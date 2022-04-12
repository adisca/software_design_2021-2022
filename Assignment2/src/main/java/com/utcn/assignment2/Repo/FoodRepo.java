package com.utcn.assignment2.Repo;

import com.utcn.assignment2.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepo extends JpaRepository<Food, Long> {

}
