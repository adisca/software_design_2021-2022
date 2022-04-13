package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Category;
import com.utcn.assignment2.Model.Food;
import com.utcn.assignment2.Model.Order;
import com.utcn.assignment2.Model.Restaurant;
import com.utcn.assignment2.Repo.RestaurantRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepo repo;

    public RestaurantService(RestaurantRepo repo) {
        this.repo = repo;
    }

    public List<Restaurant> getAll() {
        return repo.findAll();
    }

    public List<Category> getAllCategories(Long id) {
        Restaurant restaurant = repo.getById(id);
        return restaurant.getMenus();
    }

    public Boolean create(Restaurant restaurant) {
        if (repo.findByName(restaurant.getName()) == null) {
            repo.save(restaurant);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Restaurant find(Long id) {
        return repo.getById(id);
    }

    public List<Order> getAllOrders(Long id) {
        Restaurant restaurant = repo.getById(id);
        List<Order> orders = new ArrayList<>();

        for (Category menu : restaurant.getMenus()) {
            for (Food food : menu.getFoods()) {
                for (Order order : food.getOrders()) {
                    if (!orders.contains(order)) {
                        orders.add(order);
                    }
                }
            }
        }

        return orders;
    }

}
