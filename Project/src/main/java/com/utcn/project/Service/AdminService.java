package com.utcn.project.Service;

import com.utcn.project.Repo.AdminRepo;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepo repo;

    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }
}
