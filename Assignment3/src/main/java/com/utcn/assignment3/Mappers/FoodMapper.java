package com.utcn.assignment3.Mappers;

import com.utcn.assignment3.DTO.FoodDTO;
import com.utcn.assignment3.Model.Category;
import com.utcn.assignment3.Model.Food;
import com.utcn.assignment3.Util.Strategy.RoundStrategy;

import java.util.ArrayList;
import java.util.List;

public class FoodMapper {
    private RoundStrategy strategy;

    public FoodMapper(RoundStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RoundStrategy strategy) {
        this.strategy = strategy;
    }

    public List<FoodDTO> convertToDTOList(List<Food> foods) {
        List<FoodDTO> dtos = new ArrayList<>();
        for (Food food : foods) {
            FoodDTO dto = new FoodDTO();
            dto.setId(food.getId());
            dto.setName(food.getName());
            dto.setDescription(food.getDescription());
            dto.setPrice(strategy.justRound(food.getPrice()));

            dtos.add(dto);
        }
        return dtos;
    }

    public List<Food> convertFromDTOList(List<FoodDTO> dtos) {
        List<Food> foods = new ArrayList<>();
        for (FoodDTO dto : dtos) {
            Food food = new Food();
            food.setId(dto.getId());
            food.setName(dto.getName());
            food.setDescription(dto.getDescription());
            food.setPrice(strategy.justRound(dto.getPrice()));

            foods.add(food);
        }
        return foods;
    }

    public Food convertFromDTO(FoodDTO dto, Category category) {
        Food food = new Food();
        food.setName(dto.getName());
        food.setDescription(dto.getDescription());
        food.setPrice(strategy.justRound(dto.getPrice()));
        food.setBelongsTo(category);

        return food;
    }

}
