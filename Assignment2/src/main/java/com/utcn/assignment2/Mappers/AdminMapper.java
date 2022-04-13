package com.utcn.assignment2.Mappers;

import com.utcn.assignment2.DTO.LogInDTO;
import com.utcn.assignment2.Model.Admin;

public final class AdminMapper {

    private AdminMapper() {}

    public static Admin convertFromDTO(LogInDTO dto) {
        Admin admin = new Admin();
        admin.setName(dto.getCredential());
        admin.setPassword(dto.getPassword());
        return admin;
    }
}
