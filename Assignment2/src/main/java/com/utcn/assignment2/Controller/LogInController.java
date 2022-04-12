package com.utcn.assignment2.Controller;

import com.utcn.assignment2.DTO.LogInDTO;
import com.utcn.assignment2.Mappers.AdminMapper;
import com.utcn.assignment2.Mappers.ClientMapper;
import com.utcn.assignment2.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class LogInController {
    @Autowired
    private LogInService service;

    @PostMapping("/client/login")
    @ResponseBody
    public Map<String, Boolean> logInClient(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.logInClient(ClientMapper.convertFromDTO(dto)));
    }

    @PostMapping("/client/signup")
    @ResponseBody
    public Map<String, Boolean> signUpClient(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.signUpClient(ClientMapper.convertFromDTO(dto)));
    }

    @PostMapping("/admin/login")
    @ResponseBody
    public Map<String, Boolean> logInAdmin(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.logInAdmin(AdminMapper.convertFromDTO(dto)));
    }

    @PostMapping("/admin/signup")
    @ResponseBody
    public Map<String, Boolean> signUpAdmin(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.signUpAdmin(AdminMapper.convertFromDTO(dto)));
    }

}
