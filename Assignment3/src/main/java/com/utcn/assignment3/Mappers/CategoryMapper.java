package com.utcn.assignment3.Mappers;

import com.utcn.assignment3.DTO.CategoryDTO;
import com.utcn.assignment3.Model.Category;
import com.utcn.assignment3.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public final class CategoryMapper {

    private CategoryMapper() {}

    public static List<CategoryDTO> convertToDTOList(List<Category> categories) {
        List<CategoryDTO> dtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());

            dtos.add(dto);
        }
        return dtos;
    }

    public static Category convertFromDTO(CategoryDTO dto, Restaurant restaurant) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setFoods(null);
        category.setBelongsTo(restaurant);

        return category;
    }

}
