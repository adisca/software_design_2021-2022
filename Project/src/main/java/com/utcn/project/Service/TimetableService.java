package com.utcn.project.Service;

import com.utcn.project.Model.Timetable;
import com.utcn.project.Model.TimetableGroup;
import com.utcn.project.Repo.TimetableGroupRepo;
import com.utcn.project.Repo.TimetableRepo;
import com.utcn.project.Util.Strategy.StratGenProper;
import com.utcn.project.Util.Strategy.TimetableGenerationStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

@Service
public class TimetableService {
    private final TimetableRepo repoTimetable;
    private final TimetableGroupRepo repoGroup;

    private TimetableGenerationStrategy strategy;

    public TimetableService(TimetableRepo repoTimetable, TimetableGroupRepo repoGroup) {
        this.repoTimetable = repoTimetable;
        this.repoGroup = repoGroup;

        // I don't need another strategy, but I made the preparations nevertheless
        this.strategy = new StratGenProper();
    }

    public List<TimetableGroup> getGroups() {
        return repoGroup.findAll();
    }

    public Boolean changeOfficialGroup(Long id) {
        for (TimetableGroup pastOfficial : repoGroup.getAllByOfficial(true)) {
            pastOfficial.setOfficial(false);
            repoGroup.save(pastOfficial);
        }
        TimetableGroup newOfficial = repoGroup.getById(id);
        newOfficial.setOfficial(true);
        repoGroup.save(newOfficial);
        return Boolean.TRUE;
    }

    /**
     * Generates and saves the timetable groups using the "unoriginal" timetables in the database.
     * The current strategy is to collect all usable tables in a double map (matrix like) and pick
     * one at random for each user-activity pair, up to a total of 5 groups.
     *
     * Strategy design pattern.
     *
     * @return      True if success, false otherwise
     */
    @Transactional
    public Boolean generateGroups() {
        this.strategy.generate(repoGroup, repoTimetable);
        return Boolean.TRUE;
    }

    /**
     * Generates and saves new, proper, timetables that are to be used by the groups.
     * The original timetables present the user's possible time frames, from which smaller ones must be created.
     * All of these have original = false in order to be separable from the original ones.
     * The generated timetables have a 1-hour difference between them to keep the numbers manageable.
     *
     * @return      True if success, false otherwise
     */
    @Transactional
    public Boolean generateTimetables() {
        repoTimetable.deleteAllByOriginal(false);
        List<Timetable> timetables = repoTimetable.findAll();

        for (Timetable timetable : timetables) {
            LocalTime timeStart = timetable.getIntervalStart().toLocalTime();
            LocalTime timeEnd = timetable.getIntervalEnd().toLocalTime();
            LocalTime minTime = timetable.getActivity().getMinTime().toLocalTime();

            LocalTime t1 = timeStart;
            LocalTime t2 = timeStart.plusHours(minTime.getHour());
            t2 = t2.plusMinutes(minTime.getMinute());
            t2 = t2.plusSeconds(minTime.getSecond());

            while (t2.compareTo(timeEnd) <= 0) {
                Timetable newTimetable = new Timetable();

                newTimetable.setOriginal(false);
                newTimetable.setIntervalStart(Time.valueOf(t1));
                newTimetable.setIntervalEnd(Time.valueOf(t2));
                newTimetable.setUser(timetable.getUser());
                newTimetable.setActivity(timetable.getActivity());

                t1 = t1.plusHours(1);
                t2 = t2.plusHours(1);

                repoTimetable.save(newTimetable);
            }
        }
        return Boolean.TRUE;
    }

    public Boolean createTimetable(Timetable timetable) {
        // throws NullPointerProtection :))
        if (timetable == null || timetable.getActivity() == null || timetable.getUser() == null ||
                timetable.getIntervalStart() == null || timetable.getIntervalEnd() == null)
            return Boolean.FALSE;

        // Interval outside of bounds
        if (timetable.getIntervalStart().toLocalTime()
                .compareTo(timetable.getActivity().getIntervalStart().toLocalTime()) < 0 ||
                timetable.getIntervalEnd().toLocalTime()
                        .compareTo(timetable.getActivity().getIntervalEnd().toLocalTime()) > 0 ||
                timetable.getIntervalStart().toLocalTime().compareTo(timetable.getIntervalEnd().toLocalTime()) > 0)
            return Boolean.FALSE;

        // Activity full
        if (timetable.getActivity().isFull())
            return Boolean.FALSE;

        // Wrong qualification
        if (timetable.getActivity().getQualification() != null && timetable.getUser().getQualification() != null &&
                !timetable.getActivity().getQualification().equals(timetable.getUser().getQualification()))
            return Boolean.FALSE;

        // Activity already chosen by the same user
        for (Timetable table : timetable.getUser().getTimetables()) {
            if (!table.equals(timetable) && table.getActivity().equals(timetable.getActivity())) {
                return Boolean.FALSE;
            }
        }
        repoTimetable.save(timetable);
        return Boolean.TRUE;
    }

    public List<Timetable> getByUserId(Long id) {
        return repoTimetable.getAllByUserIdAndOriginal(id, true);
    }

    public List<Timetable> getUserOfficial(Long id) {
        List<Timetable> timetables = new ArrayList<>();
        for (TimetableGroup group : repoGroup.getAllByOfficial(true)) {
            for (Timetable timetable : group.getTimetables()) {
                if (Objects.equals(timetable.getUser().getId(), id))
                    timetables.add(timetable);
            }
        }
        return timetables;
    }
}
