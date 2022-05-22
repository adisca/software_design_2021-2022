package com.utcn.project.Controller;

import com.utcn.project.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    @Autowired
    private ActivityService service;
}
