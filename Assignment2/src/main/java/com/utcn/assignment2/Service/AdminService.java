package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Restaurant;
import com.utcn.assignment2.Repo.AdminRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepo repo;

    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }

}
