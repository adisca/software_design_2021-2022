package com.utcn.project.Mapper;

import com.utcn.project.DTO.UserDTO;
import com.utcn.project.Model.Enums.Qualification;
import com.utcn.project.Model.User;

public final class UserMapper {

    private UserMapper() {}

    public static User convertFromDTO(UserDTO dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setQualification(Qualification.valueOf(dto.getQualification()));

        return user;
    }

    public static UserDTO convertToDTO(User user) {
        if (user == null)
            return null;

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setQualification(user.getQualification().toString());

        return dto;
    }

}
