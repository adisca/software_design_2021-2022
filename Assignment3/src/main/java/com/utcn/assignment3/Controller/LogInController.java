package com.utcn.assignment3.Controller;

import com.utcn.assignment3.DTO.LogInDTO;
import com.utcn.assignment3.Mappers.AdminMapper;
import com.utcn.assignment3.Mappers.ClientMapper;
import com.utcn.assignment3.Service.LogInService;
import com.utcn.assignment3.Util.JwtUtil;
import com.utcn.assignment3.Util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LogInController {
    @Autowired
    private LogInService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/client/login")
    @ResponseBody
    public Map<String, String> logInClient(@RequestBody LogInDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getCredential(), dto.getPassword()));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return Collections.singletonMap("token", "");
        }

        ClientMapper clientMapper = ClientMapper.getInstance();
        Long userID = service.logInClient(clientMapper.convertFromLogInDTO(dto));
        if (userID != -1) {
            UserDetails userDetails = service.loadUserByUsername(dto.getCredential());
            String jwt = JwtUtil.generateToken(userDetails, userID, Role.ROLE_client);
            return Collections.singletonMap("token", jwt);
        }
        return Collections.singletonMap("token", "");
    }

    @PostMapping("/client/signup")
    @ResponseBody
    public Map<String, Boolean> signUpClient(@RequestBody LogInDTO dto) {
        ClientMapper clientMapper = ClientMapper.getInstance();
        return Collections.singletonMap("status", service.signUpClient(clientMapper.convertFromLogInDTO(dto)));
    }

    @PostMapping("/admin/login")
    @ResponseBody
    public Map<String, String> logInAdmin(@RequestBody LogInDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getCredential(), dto.getPassword()));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return Collections.singletonMap("token", "");
        }

        Long userID = service.logInAdmin(AdminMapper.convertFromDTO(dto));
        if (userID != -1) {
            UserDetails userDetails = service.loadUserByUsername(dto.getCredential());
            String jwt = JwtUtil.generateToken(userDetails, userID, Role.ROLE_admin);
            return Collections.singletonMap("token", jwt);
        }
        return Collections.singletonMap("token", "");
    }

    @PostMapping("/admin/signup")
    @ResponseBody
    public Map<String, Boolean> signUpAdmin(@RequestBody LogInDTO dto) {
        return Collections.singletonMap("status", service.signUpAdmin(AdminMapper.convertFromDTO(dto)));
    }

}
