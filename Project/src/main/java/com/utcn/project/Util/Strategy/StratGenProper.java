package com.utcn.project.Util.Strategy;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Timetable;
import com.utcn.project.Model.TimetableGroup;
import com.utcn.project.Model.User;
import com.utcn.project.Repo.TimetableGroupRepo;
import com.utcn.project.Repo.TimetableRepo;

import java.util.*;

public class StratGenProper implements TimetableGenerationStrategy {

    @Override
    public void generate(TimetableGroupRepo repoGroup, TimetableRepo repoTimetable) {
        repoGroup.deleteAll();

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
                    if (timetables.size() > 0)
                        miniGroups.add(timetables.get(rand.nextInt(timetables.size())));
                }
            }

            group.setTimetables(miniGroups);
            group.setOfficial(false);

            repoGroup.save(group);
        }
    }
}
