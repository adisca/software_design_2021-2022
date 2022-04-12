package com.utcn.assignment2.Repo;

import com.utcn.assignment2.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    Admin findByName(String name);

}
