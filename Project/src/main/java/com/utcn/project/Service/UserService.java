package com.utcn.project.Service;

import com.utcn.project.Model.User;
import com.utcn.project.Repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User getById(Long id) {
        return repo.getById(id);
    }
}
