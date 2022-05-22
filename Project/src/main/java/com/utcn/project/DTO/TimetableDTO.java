package com.utcn.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO {
    private Long id;
    private UserDTO user;
    private ActivityDTO activity;
    private String day;
}
