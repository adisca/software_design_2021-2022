package com.utcn.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimetableGroupDTO {
    private Long id;
    private Boolean official;
    private List<TimetableDTO> timetables;
}
