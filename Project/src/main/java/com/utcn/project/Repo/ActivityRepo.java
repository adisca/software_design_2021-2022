package com.utcn.project.Repo;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Enums.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepo extends JpaRepository<Activity, Long> {

    List<Activity> findAllByQualification(Qualification qualification);

}
