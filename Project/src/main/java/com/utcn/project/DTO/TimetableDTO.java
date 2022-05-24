package com.utcn.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO {
    private Long id;
    private Time intervalStart;
    private Time intervalEnd;
    private UserDTO user;
    private ActivityDTO activity;
}
