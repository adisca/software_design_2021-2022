package com.utcn.assignment3.Repo;

import com.utcn.assignment3.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
