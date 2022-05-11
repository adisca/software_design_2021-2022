package com.utcn.assignment3.Mappers;

import com.utcn.assignment3.DTO.AdminDTO;
import com.utcn.assignment3.DTO.LogInDTO;
import com.utcn.assignment3.Model.Admin;

public final class AdminMapper {

    private AdminMapper() {}

    public static Admin convertFromDTO(LogInDTO dto) {
        Admin admin = new Admin();
        admin.setName(dto.getCredential());
        admin.setPassword(dto.getPassword());
        return admin;
    }

    public static AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        return dto;
    }
}
