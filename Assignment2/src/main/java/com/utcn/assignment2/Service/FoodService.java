package com.utcn.assignment2.Service;

import com.utcn.assignment2.DTO.FoodDTO;
import com.utcn.assignment2.Model.Food;
import com.utcn.assignment2.Repo.FoodRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Food> findCorespondent(List<Food> foods) {
        List<Food> realFoods = repo.findAll();
        ArrayList<Food> result = new ArrayList<>();
        for (Food food : foods) {
            for (Food realFood : realFoods) {
                if (Objects.equals(realFood.getId(), food.getId())) {
                    result.add(realFood);
                    break;
                }
            }
        }
        return result;
    }
}
