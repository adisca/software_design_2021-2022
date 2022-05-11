package com.utcn.assignment3.Service;

import com.utcn.assignment3.Model.Admin;
import com.utcn.assignment3.Model.Restaurant;
import com.utcn.assignment3.Repo.AdminRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for admin
 */
@Service
public class AdminService {
    private final AdminRepo repo;

    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }

    /**
     * Gets a list of owned restaurants.
     *
     * @param id    id of the admin
     * @return      list of the admin's restaurants
     */
    public List<Restaurant> getRestaurants(Long id) {
        Optional<Admin> admin = repo.findById(id);
        if (admin.isPresent())
            return admin.get().getRestaurants();
        return new ArrayList<>();
    }

    /**
     * Gets an admin by his id.
     *
     * @param id    the admin's id
     * @return      the admin found or null otherwise
     */
    public Admin getById(Long id) {
        Optional<Admin> admin = repo.findById(id);
        return admin.orElse(null);
    }
}
