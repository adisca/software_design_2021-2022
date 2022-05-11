package com.utcn.assignment3.Repo;

import com.utcn.assignment3.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<Food, Long> {

}
