package com.utcn.project.Controller;

import com.utcn.project.Service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimetableController {
    @Autowired
    private TimetableService service;
}
