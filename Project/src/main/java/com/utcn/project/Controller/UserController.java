package com.utcn.project.Controller;

import com.utcn.project.Service.ActivityService;
import com.utcn.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
}
