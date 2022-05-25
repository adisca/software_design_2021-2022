package com.utcn.project.Controller;

import com.utcn.project.DTO.LogInDTO;
import com.utcn.project.DTO.UserDTO;
import com.utcn.project.Mapper.LogInMapper;
import com.utcn.project.Mapper.UserMapper;
import com.utcn.project.Service.LogInService;
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
    public LogInService service;

    @PostMapping("/login/user")
    @ResponseBody
    public Map<String, UserDTO> logInUser(@RequestBody LogInDTO dto) {
        UserMapper mapper = UserMapper.getInstance();
        return Collections.singletonMap(
                "status", mapper.convertToDTO(service.logInUser(LogInMapper.convertFromDTOUser(dto)))
        );
    }

    @PostMapping("/login/admin")
    @ResponseBody
    public Map<String, Long> logInAdmin(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.logInAdmin(LogInMapper.convertFromDTOAdmin(dto)));
    }

    @PostMapping("/signup/user")
    @ResponseBody
    public Map<String, Boolean> signUpUser(@RequestBody UserDTO dto) {
        UserMapper mapper = UserMapper.getInstance();
        return Collections.singletonMap("status", service.signUpUser(mapper.convertFromDTO(dto)));
    }

    @PostMapping("/signup/admin")
    @ResponseBody
    public Map<String, Boolean> signUpAdmin(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.signUpAdmin(LogInMapper.convertFromDTOAdmin(dto)));
    }

}
