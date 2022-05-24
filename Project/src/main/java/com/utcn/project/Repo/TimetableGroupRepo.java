package com.utcn.project.Repo;

import com.utcn.project.Model.TimetableGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimetableGroupRepo extends JpaRepository<TimetableGroup, Long> {

    void deleteAllByOfficial(Boolean official);

    List<TimetableGroup> getAllByOfficial(Boolean official);
}
