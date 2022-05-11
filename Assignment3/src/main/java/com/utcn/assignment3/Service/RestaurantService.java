package com.utcn.assignment3.Service;

import com.utcn.assignment3.Model.*;
import com.utcn.assignment3.Repo.RestaurantRepo;
import com.utcn.assignment3.Util.Exporter;
import com.utcn.assignment3.Util.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for restaurants
 */
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

    /**
     * Saves to the database a new restaurant
     *
     * @param restaurant    The restaurant details to be added
     * @return              True if success, false otherwise
     */
    public Boolean create(Restaurant restaurant) {
        if (repo.findByName(restaurant.getName()) == null) {
            repo.save(restaurant);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Searches for a restaurant by id.
     *
     * @param id    The id of the restaurant
     * @return      The restaurant if found, null otherwise
     */
    public Restaurant find(Long id) {
        return repo.getById(id);
    }

    /**
     * Gets all orders of a restaurant.
     *
     * @param id    The id of the restaurant
     * @return      A list of all orders belonging to the restaurant
     */
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

    /**
     * Gets all orders of a restaurant that have the right status.
     *
     * @param id        The id of the restaurant
     * @param filter    The status in string format to be filtered by
     * @return          A list of orders that match the status filter
     */
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

    /**
     * Returns a list of restaurants that match the filter.
     * In this case, the restaurant must be an exact match with the name
     *
     * @param name  The name to be filtered by
     * @return      A list of matching restaurants
     */
    public List<Restaurant> getFiltered(String name) {
        ArrayList<Restaurant> result = new ArrayList<>();
        Restaurant rest = repo.findByName(name);
        if (rest != null)
            result.add(rest);
        return result;
    }

    /**
     * Exports the menu (all categories and their foods) of a restaurant to a PDF.
     * Details of the conversion are decided by the Exporter class.
     *
     * @param id    the id of the restaurant
     * @return      True if success, false otherwise
     */
    public Boolean exportPDF(Long id) {
        Optional<Restaurant> rest = repo.findById(id);
        if(rest.isPresent()) {
            try {
                Exporter.exportPDF(rest.get());
                return Boolean.TRUE;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Boolean.FALSE;
    }
}
