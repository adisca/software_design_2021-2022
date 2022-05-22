package com.utcn.project.Service;

import com.utcn.project.Model.TimetableGroup;
import com.utcn.project.Model.User;
import com.utcn.project.Repo.TimetableGroupRepo;
import com.utcn.project.Repo.TimetableRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {
    private TimetableRepo repoTimetable;
    private TimetableGroupRepo repoGroup;

    public TimetableService(TimetableRepo repoTimetable, TimetableGroupRepo repoGroup) {
        this.repoTimetable = repoTimetable;
        this.repoGroup = repoGroup;
    }

    public List<TimetableGroup> getGroups() {
        return null;
    }

    public Boolean generateTimetables(List<User> users) {
        // Logic
        return null;
    }

}
