package com.utcn.assignment2.Mappers;

import com.utcn.assignment2.DTO.LogInDTO;
import com.utcn.assignment2.DTO.RestaurantDTO;
import com.utcn.assignment2.Model.Client;
import com.utcn.assignment2.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public final class RestaurantMapper {

    private RestaurantMapper() {}

    public static List<RestaurantDTO> convertToDTOList(List<Restaurant> restaurants) {
        List<RestaurantDTO> dtos = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantDTO dto = new RestaurantDTO();
            dto.setId(restaurant.getId());
            dto.setName(restaurant.getName());

            dtos.add(dto);
        }
        return dtos;
    }

    public static Restaurant convertFromDTO(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setMenus(null);

        return restaurant;
    }

}
