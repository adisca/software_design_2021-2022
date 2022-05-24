package com.utcn.project.Service;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Enums.Qualification;
import com.utcn.project.Repo.ActivityRepo;
import org.springframework.stereotype.Service;

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

    public List<Activity> getByQualification(Qualification qualification) {
        return repo.findAllByQualification(qualification);
    }

    public Boolean create(Activity activity) {
        repo.save(activity);
        return Boolean.TRUE;
    }

    public Activity getById(Long id) {
        return repo.getById(id);
    }

}
