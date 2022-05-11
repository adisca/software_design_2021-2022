package com.utcn.assignment3.Service;

import com.utcn.assignment3.Model.Food;
import com.utcn.assignment3.Repo.FoodRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service class for food
 */
@Service
public class FoodService {
    private final FoodRepo repo;

    public FoodService(FoodRepo repo) {
        this.repo = repo;
    }

    /**
     * Adds to the database a new food
     *
     * @param food  The food details to be added
     * @return      True if success, false otherwise
     */
    public Boolean create(Food food) {
        for (Food food1 : food.getBelongsTo().getFoods()) {
            if (Objects.equals(food.getName(), food1.getName()) && food != food1) {
                return Boolean.FALSE;
            }
        }
        repo.save(food);
        return Boolean.TRUE;
    }

    /**
     * Finds the database correspondents of a list of foods. The primary reason
     * is that the ids (and other attributes absent in the dto) must be gathered
     * from the database.
     *
     * @param foods A list of foods to have their correspondents found
     * @return      A list of the real foods, as found in the database
     */
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
