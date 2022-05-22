package com.utcn.project.Controller;

import com.utcn.project.DTO.LogInDTO;
import com.utcn.project.DTO.UserDTO;
import com.utcn.project.Service.AdminService;
import com.utcn.project.Service.LogInService;
import com.utcn.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LogInController {
    @Autowired
    public LogInService service;
    @Autowired
    public UserService userService;
    @Autowired
    public AdminService adminService;

    @PostMapping("/login/user")
    public Map<String, Boolean> logInUser(LogInDTO dto) {

        return null;
    }

    @PostMapping("/login/admin")
    public Map<String, Boolean> logInAdmin(LogInDTO dto) {

        return null;
    }

    @PostMapping("/signup/user")
    public Map<String, Boolean> signUpUser(UserDTO dto) {

        return null;
    }

    @PostMapping("/signup/admin")
    public Map<String, Boolean> signUpAdmin(LogInDTO dto) {

        return null;
    }

}
