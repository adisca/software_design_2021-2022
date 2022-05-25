package com.utcn.project.Repo;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Enums.Day;
import com.utcn.project.Model.Enums.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepo extends JpaRepository<Activity, Long> {

    List<Activity> findAllByQualification(Qualification qualification);
    List<Activity> findAllByNameAndQualification(String name, Qualification qualification);
    List<Activity> findAllByDayAndQualification(Day day, Qualification qualification);
    List<Activity> findAllByNameAndDayAndQualification(String name , Day day, Qualification qualification);

}
