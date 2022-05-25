package com.utcn.project.Service;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Enums.Day;
import com.utcn.project.Model.Enums.Qualification;
import com.utcn.project.Repo.ActivityRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    private final ActivityRepo repo;

    public ActivityService(ActivityRepo repo) {
        this.repo = repo;
    }

    public List<Activity> getAll() {
        return repo.findAll();
    }

    public List<Activity> getFiltered(String name, Day day, Qualification qualification) {
        List<Activity> results = new ArrayList<>();

        if (day == null && name == null)
            results.addAll(repo.findAllByQualification(qualification));
        else if (day == null)
            results.addAll(repo.findAllByNameAndQualification(name, qualification));
        else if (name == null) {
            results.addAll(repo.findAllByDayAndQualification(day, qualification));
            results.addAll(repo.findAllByDayAndQualification(null, qualification));
        }
        else {
            results.addAll(repo.findAllByNameAndDayAndQualification(name, day, qualification));
            results.addAll(repo.findAllByNameAndDayAndQualification(name, null, qualification));
        }
        results.removeIf(Activity::isFull);
        return results;
    }

    public Boolean create(Activity activity) {
        if (activity.getName() == null || activity.getName().equals("") || activity.getNbPeople() == null)
            return Boolean.FALSE;
        repo.save(activity);
        return Boolean.TRUE;
    }

    public Activity getById(Long id) {
        return repo.getById(id);
    }

}
