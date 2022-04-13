package com.utcn.project.Repo;

import com.utcn.project.Model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepo extends JpaRepository<Timetable, Long> {
}
