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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/restaurant")
    @ResponseBody
    public List<RestaurantDTO> getAllRestaurants() {
        return RestaurantMapper.convertToDTOList(restaurantService.getAll());
    }

    @PostMapping("/restaurant")
    @ResponseBody
    public Map<String, Boolean> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return Collections.singletonMap("status", restaurantService.create(RestaurantMapper.convertFromDTO(restaurantDTO)));
    }

    @GetMapping("/restaurant/{id}")
    @ResponseBody
    public List<CategoryDTO> getAllCategoriesOfRestaurant(@PathVariable Long id) {
        return CategoryMapper.convertToDTOList(restaurantService.getAllCategories(id));
    }

    @PostMapping("/restaurant/{id}")
    @ResponseBody
    public Map<String, Boolean> addCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return Collections.singletonMap("status",
                categoryService.create(CategoryMapper.convertFromDTO(categoryDTO, restaurantService.find(id))));
    }

    @GetMapping("/restaurant/{id}/orders")
    @ResponseBody
    public List<OrderDTO> getAllOrdersOfRestaurant(@PathVariable Long id) {
        return OrderMapper.convertToDTOList(restaurantService.getAllOrders(id));
    }

    @GetMapping("/orders/{id}/accept")
    @ResponseBody
    public Map<String, Boolean> acceptOrder(@PathVariable Long id) {
        return Collections.singletonMap("status", orderService.accept(id));
    }

    @GetMapping("/orders/{id}/decline")
    @ResponseBody
    public Map<String, Boolean> declineOrder(@PathVariable Long id) {
        return Collections.singletonMap("status", orderService.decline(id));
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public List<FoodDTO> getAllFoodsOfCategory(@PathVariable Long id) {
        return FoodMapper.convertToDTOList(categoryService.getAllFoods(id));
    }

    @PostMapping("/category/{id}")
    @ResponseBody
    public Map<String, Boolean> addFood(@PathVariable Long id, @RequestBody FoodDTO foodDTO) {
        return Collections.singletonMap("status",
                foodService.create(FoodMapper.convertFromDTO(foodDTO, categoryService.find(id))));
    }

}
