package com.utcn.project.Repo;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Timetable;
import com.utcn.project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimetableRepo extends JpaRepository<Timetable, Long> {

    void deleteAllByOriginal(Boolean original);

    List<Timetable> getAllByOriginal(Boolean original);
    List<Timetable> getAllByUserAndActivityAndOriginal(User user, Activity activity, Boolean original);

}
