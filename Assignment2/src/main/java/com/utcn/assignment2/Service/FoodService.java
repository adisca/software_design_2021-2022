package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Category;
import com.utcn.assignment2.Model.Food;
import com.utcn.assignment2.Repo.FoodRepo;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FoodService {
    private final FoodRepo repo;

    public FoodService(FoodRepo repo) {
        this.repo = repo;
    }

    public Boolean create(Food food) {
        for (Food food1 : food.getBelongsTo().getFoods()) {
            if (Objects.equals(food.getName(), food1.getName()) && food != food1) {
                return Boolean.FALSE;
            }
        }
        repo.save(food);
        return Boolean.TRUE;
    }
}
