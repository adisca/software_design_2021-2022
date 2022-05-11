package com.utcn.assignment3.Controller;

import com.utcn.assignment3.DTO.CategoryDTO;
import com.utcn.assignment3.DTO.FoodDTO;
import com.utcn.assignment3.DTO.RestaurantDTO;
import com.utcn.assignment3.Mappers.CategoryMapper;
import com.utcn.assignment3.Mappers.FoodMapper;
import com.utcn.assignment3.Mappers.RestaurantMapper;
import com.utcn.assignment3.Service.*;
import com.utcn.assignment3.Util.Strategy.StratFair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
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
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/{id}")
    @ResponseBody
    public List<RestaurantDTO> getAllRestaurantsOfAdmin(@PathVariable Long id) {
        return RestaurantMapper.convertToDTOList(adminService.getRestaurants(id));
    }

    @PostMapping("/admin/{id}")
    @ResponseBody
    public Map<String, Boolean> addRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO restaurantDTO) {
        return Collections.singletonMap("status",
                restaurantService.create(RestaurantMapper.convertFromDTO(restaurantDTO, adminService.getById(id))));
    }

    @PostMapping("/restaurant/{id}")
    @ResponseBody
    public Map<String, Boolean> addCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return Collections.singletonMap("status",
                categoryService.create(CategoryMapper.convertFromDTO(categoryDTO, restaurantService.find(id))));
    }

    @GetMapping("/restaurant/{id}/pdf")
    @ResponseBody
    public Map<String, Boolean> exportMenuToPDF(@PathVariable Long id) {
        return Collections.singletonMap("status", restaurantService.exportPDF(id));
    }

    @PostMapping("/orders/{id}/{status}")
    @ResponseBody
    public Map<String, Boolean> acceptOrder(@PathVariable Long id, @PathVariable String status) {
        return Collections.singletonMap("status", orderService.changeStatus(id, status));
    }

    @PostMapping("/category/{id}")
    @ResponseBody
    public Map<String, Boolean> addFood(@PathVariable Long id, @RequestBody FoodDTO foodDTO) {
        FoodMapper foodMapper = new FoodMapper(new StratFair());
        return Collections.singletonMap("status",
                foodService.create(foodMapper.convertFromDTO(foodDTO, categoryService.find(id))));
    }

}
