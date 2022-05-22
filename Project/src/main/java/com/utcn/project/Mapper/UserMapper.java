package com.utcn.project.Mapper;

import com.utcn.project.DTO.UserDTO;
import com.utcn.project.Model.Enums.Qualification;
import com.utcn.project.Model.User;

public final class UserMapper {

    public static User convertFromDTO(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setQualification(Qualification.valueOf(dto.getQualification()));
        return user;
    }

}
