package com.utcn.project.Service;

import com.utcn.project.Repo.ActivityRepo;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    private ActivityRepo repo;

    public ActivityService(ActivityRepo repo) {
        this.repo = repo;
    }
}
