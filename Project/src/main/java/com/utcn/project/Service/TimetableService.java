package com.utcn.project.Service;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Timetable;
import com.utcn.project.Model.TimetableGroup;
import com.utcn.project.Model.User;
import com.utcn.project.Repo.TimetableGroupRepo;
import com.utcn.project.Repo.TimetableRepo;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

@Service
public class TimetableService {
    private final TimetableRepo repoTimetable;
    private final TimetableGroupRepo repoGroup;

    public TimetableService(TimetableRepo repoTimetable, TimetableGroupRepo repoGroup) {
        this.repoTimetable = repoTimetable;
        this.repoGroup = repoGroup;
    }

    public List<TimetableGroup> getGroups() {
        return repoGroup.findAll();
    }

    public void changeOfficialGroup(Long id) {
        for (TimetableGroup pastOfficial : repoGroup.getAllByOfficial(true)) {
            pastOfficial.setOfficial(false);
        }
        TimetableGroup newOfficial = repoGroup.getById(id);
        newOfficial.setOfficial(true);
    }

    public void generateGroups() {
        repoGroup.deleteAllByOfficial(false);

        Map<Activity, Map<User, List<Timetable>>> m = new HashMap<>();

        for (Timetable ogTimetable : repoTimetable.getAllByOriginal(true)) {

            m.putIfAbsent(ogTimetable.getActivity(), new HashMap<>());
            m.get(ogTimetable.getActivity()).putIfAbsent(ogTimetable.getUser(), new ArrayList<>());

            for (Timetable genTimetable : repoTimetable.getAllByUserAndActivityAndOriginal(
                    ogTimetable.getUser(), ogTimetable.getActivity(), false)) {
                m.get(ogTimetable.getActivity()).get(ogTimetable.getUser()).add(genTimetable);
            }
        }

        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            TimetableGroup group = new TimetableGroup();
            List<Timetable> miniGroups = new ArrayList<>();
            for (Map<User, List<Timetable>> value : m.values()) {
                for (List<Timetable> timetables : value.values()) {
                    miniGroups.add(timetables.get(rand.nextInt(timetables.size())));
                }
            }
            group.setTimetables(miniGroups);
            group.setOfficial(false);

            repoGroup.save(group);
        }
    }


    public void generateTimetables() {
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
    }

    public Boolean createTimetable(Timetable timetable) {
        if (timetable.getActivity().isFull())
            return Boolean.FALSE;
        if (timetable.getActivity().getQualification() != null && timetable.getUser().getQualification() != null &&
                !timetable.getActivity().getQualification().equals(timetable.getUser().getQualification()))
            return Boolean.FALSE;
        for (Timetable table : timetable.getUser().getTimetables()) {
            if (!table.equals(timetable) && table.getActivity().equals(timetable.getActivity())) {
                return Boolean.FALSE;
            }
        }
        repoTimetable.save(timetable);
        return Boolean.TRUE;
    }
}
