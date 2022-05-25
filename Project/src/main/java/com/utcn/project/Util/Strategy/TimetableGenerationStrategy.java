package com.utcn.project.Util.Strategy;

import com.utcn.project.Repo.TimetableGroupRepo;
import com.utcn.project.Repo.TimetableRepo;

public interface TimetableGenerationStrategy {
    void generate(TimetableGroupRepo repoGroup, TimetableRepo repoTimetable);
}
