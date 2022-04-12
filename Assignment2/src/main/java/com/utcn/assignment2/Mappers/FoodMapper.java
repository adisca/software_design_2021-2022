package com.utcn.assignment2.Mappers;

import com.utcn.assignment2.DTO.FoodDTO;
import com.utcn.assignment2.Model.Category;
import com.utcn.assignment2.Model.Food;

import java.util.ArrayList;
import java.util.List;

public final class FoodMapper {

    private FoodMapper() {}

    public static List<FoodDTO> convertToDTOList(List<Food> foods) {
        List<FoodDTO> dtos = new ArrayList<>();
        for (Food food : foods) {
            FoodDTO dto = new FoodDTO();
            dto.setId(food.getId());
            dto.setName(food.getName());
            dto.setDescription(food.getDescription());
            dto.setPrice(food.getPrice());

            dtos.add(dto);
        }
        return dtos;
    }

    public static Food convertFromDTO(FoodDTO dto, Category category) {
        Food food = new Food();
        food.setName(dto.getName());
        food.setDescription(dto.getDescription());
        food.setPrice(dto.getPrice());
        food.setBelongsTo(category);

        return food;
    }

}
