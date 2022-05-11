package com.utcn.assignment3.Repo;

import com.utcn.assignment3.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    Admin findByName(String name);
    Optional<Admin> findById(Long id);

}
