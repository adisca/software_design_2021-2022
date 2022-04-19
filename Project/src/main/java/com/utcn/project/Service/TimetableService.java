package com.utcn.project.Service;

import com.utcn.project.BusinessLogic.TimetableLogic;
import com.utcn.project.Model.TimetableGroup;
import com.utcn.project.Model.User;
import com.utcn.project.Repo.TimetableGroupRepo;
import com.utcn.project.Repo.TimetableRepo;

import java.util.List;

public class TimetableService {
    private TimetableRepo repoTimetable;
    private TimetableGroupRepo repoGroup;

    public List<TimetableGroup> getGroups() {
        return null;
    }

    public Boolean generateTimetables(List<User> users) {
        TimetableLogic.generateTimetables(users);
        return null;
    }

}
