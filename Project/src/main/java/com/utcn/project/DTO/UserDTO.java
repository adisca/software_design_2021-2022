package com.utcn.project.DTO;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Qualification;

import java.util.List;

public class UserDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private Qualification qualification;
    private List<ActivityDTO> activities;
}
