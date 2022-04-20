package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Admin;
import com.utcn.assignment2.Model.Restaurant;
import com.utcn.assignment2.Repo.AdminRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepo repo;

    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }

    public List<Restaurant> getRestaurants(Long id) {
        Optional<Admin> admin = repo.findById(id);
        if (admin.isPresent())
            return admin.get().getRestaurants();
        return new ArrayList<>();
    }

    public Admin getById(Long id) {
        Optional<Admin> admin = repo.findById(id);
        return admin.orElse(null);
    }
}
