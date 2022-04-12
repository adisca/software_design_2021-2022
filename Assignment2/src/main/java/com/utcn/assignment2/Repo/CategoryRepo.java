package com.utcn.assignment2.Repo;

import com.utcn.assignment2.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
