package com.utcn.project.Controller;

import com.utcn.project.DTO.LogInDTO;
import com.utcn.project.Service.LogInService;
import com.utcn.project.Service.UserService;

import java.util.Map;

public class LogInController {
    public LogInService service;
    public UserService userService;

    public Map<String, Boolean> logIn(LogInDTO dto) {
        // Some mapper just for the package connection

        return null;
    }

    public Map<String, Boolean> logOut() {
        return null;
    }

    public Map<String, Boolean> signUp(LogInDTO dto) {
        return null;
    }

}
