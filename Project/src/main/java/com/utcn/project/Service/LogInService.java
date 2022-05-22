package com.utcn.project.Service;

import com.utcn.project.Repo.AdminRepo;
import com.utcn.project.Repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class LogInService {
    private UserRepo userRepo;
    private AdminRepo adminRepo;

    public LogInService(UserRepo userRepo, AdminRepo adminRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }
}
