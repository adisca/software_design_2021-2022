package com.utcn.project.Service;

import com.utcn.project.Repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }
}
