package com.utcn.assignment2.Controller;

import com.utcn.assignment2.DTO.LogInDTO;
import com.utcn.assignment2.Mappers.AdminMapper;
import com.utcn.assignment2.Mappers.ClientMapper;
import com.utcn.assignment2.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class LogInController {
    @Autowired
    private LogInService service;

    @PostMapping("/client/login")
    @ResponseBody
    public Map<String, Long> logInClient(@RequestBody LogInDTO dto) {
        ClientMapper clientMapper = ClientMapper.getInstance();
        return Collections.singletonMap("id", service.logInClient(clientMapper.convertFromLogInDTO(dto)));
    }

    @PostMapping("/client/signup")
    @ResponseBody
    public Map<String, Boolean> signUpClient(@RequestBody LogInDTO dto) {
        ClientMapper clientMapper = ClientMapper.getInstance();
        return Collections.singletonMap("status", service.signUpClient(clientMapper.convertFromLogInDTO(dto)));
    }

    @PostMapping("/admin/login")
    @ResponseBody
    public Map<String, Long> logInAdmin(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("id", service.logInAdmin(AdminMapper.convertFromDTO(dto)));
    }

    @PostMapping("/admin/signup")
    @ResponseBody
    public Map<String, Boolean> signUpAdmin(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.signUpAdmin(AdminMapper.convertFromDTO(dto)));
    }

}
