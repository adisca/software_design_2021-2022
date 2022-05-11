package com.utcn.assignment3.Repo;

import com.utcn.assignment3.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Restaurant findByName(String name);
}
