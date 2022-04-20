package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.*;
import com.utcn.assignment2.Repo.RestaurantRepo;
import com.utcn.assignment2.Util.OrderStatus;
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

    public List<Order> getFilteredOrders(Long id, String filter) {
        Restaurant restaurant = repo.getById(id);
        List<Order> orders = new ArrayList<>();
        OrderStatus filterEnum = OrderStatus.valueOf(filter);

        for (Category menu : restaurant.getMenus()) {
            for (Food food : menu.getFoods()) {
                for (Order order : food.getOrders()) {
                    if (!orders.contains(order) && order.getStatus() == filterEnum) {
                        orders.add(order);
                    }
                }
            }
        }
        return orders;
    }

    public List<Restaurant> getFiltered(String name) {
        ArrayList<Restaurant> result = new ArrayList<>();
        Restaurant rest = repo.findByName(name);
        if (rest != null)
            result.add(rest);
        return result;
    }
}
