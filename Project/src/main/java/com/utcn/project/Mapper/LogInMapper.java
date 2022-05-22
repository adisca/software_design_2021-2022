package com.utcn.project.Mapper;

import com.utcn.project.DTO.LogInDTO;
import com.utcn.project.Model.Admin;
import com.utcn.project.Model.User;

public final class LogInMapper {

    public static User convertFromDTOUser(LogInDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static Admin convertFromDTOAdmin(LogInDTO dto) {
        Admin admin = new Admin();
        admin.setUsername(dto.getUsername());
        admin.setPassword(dto.getPassword());
        return admin;
    }
}
