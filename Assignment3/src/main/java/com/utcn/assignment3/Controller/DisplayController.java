package com.utcn.assignment3.Controller;

import com.utcn.assignment3.DTO.CategoryDTO;
import com.utcn.assignment3.DTO.FoodDTO;
import com.utcn.assignment3.DTO.OrderDTO;
import com.utcn.assignment3.DTO.RestaurantDTO;
import com.utcn.assignment3.Mappers.CategoryMapper;
import com.utcn.assignment3.Mappers.FoodMapper;
import com.utcn.assignment3.Mappers.OrderMapper;
import com.utcn.assignment3.Mappers.RestaurantMapper;
import com.utcn.assignment3.Service.*;
import com.utcn.assignment3.Util.Strategy.StratAbusePsychology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
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
        System.out.println("Get rests");
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
