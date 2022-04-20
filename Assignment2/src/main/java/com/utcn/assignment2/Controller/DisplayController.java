package com.utcn.assignment2.Controller;

import com.utcn.assignment2.DTO.CategoryDTO;
import com.utcn.assignment2.DTO.FoodDTO;
import com.utcn.assignment2.DTO.OrderDTO;
import com.utcn.assignment2.DTO.RestaurantDTO;
import com.utcn.assignment2.Mappers.CategoryMapper;
import com.utcn.assignment2.Mappers.FoodMapper;
import com.utcn.assignment2.Mappers.OrderMapper;
import com.utcn.assignment2.Mappers.RestaurantMapper;
import com.utcn.assignment2.Service.*;
import com.utcn.assignment2.Util.Strategy.StratAbusePsychology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DisplayController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/restaurant/{id}/orders")
    @ResponseBody
    public List<OrderDTO> getAllOrdersOfRestaurant(@PathVariable Long id) {
        return OrderMapper.convertToDTOList(restaurantService.getAllOrders(id));
    }

    @GetMapping("/restaurant/{id}/orders/{filter}")
    @ResponseBody
    public List<OrderDTO> getFilteredOrdersOfRestaurant(@PathVariable Long id, @PathVariable String filter) {
        return OrderMapper.convertToDTOList(restaurantService.getFilteredOrders(id, filter));
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public List<FoodDTO> getAllFoodsOfCategory(@PathVariable Long id) {
        FoodMapper foodMapper = new FoodMapper(new StratAbusePsychology());
        return foodMapper.convertToDTOList(categoryService.getAllFoods(id));
    }

    @GetMapping("/restaurant")
    @ResponseBody
    public List<RestaurantDTO> getAllRestaurants() {
        return RestaurantMapper.convertToDTOList(restaurantService.getAll());
    }

    @GetMapping("/restaurant/search/{filter}")
    @ResponseBody
    public List<RestaurantDTO> getFilteredRestaurants(@PathVariable String filter) {
        return RestaurantMapper.convertToDTOList(restaurantService.getFiltered(filter));
    }

    @GetMapping("/restaurant/{id}")
    @ResponseBody
    public List<CategoryDTO> getAllCategoriesOfRestaurant(@PathVariable Long id) {
        return CategoryMapper.convertToDTOList(restaurantService.getAllCategories(id));
    }
}
