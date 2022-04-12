package com.utcn.assignment2.Repo;

import com.utcn.assignment2.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);
}
