package com.utcn.project.Mapper;

import com.utcn.project.DTO.TimetableDTO;
import com.utcn.project.DTO.TimetableGroupDTO;
import com.utcn.project.Model.Timetable;
import com.utcn.project.Model.TimetableGroup;

import java.util.ArrayList;
import java.util.List;

public final class TimetableGroupMapper {

    private TimetableGroupMapper() {}


    public static List<TimetableGroupDTO> convertToDTOList(List<TimetableGroup> groups) {
        List<TimetableGroupDTO> dtos = new ArrayList<>();
        for (TimetableGroup group : groups) {
            dtos.add(convertToDTO(group));
        }
        return dtos;
    }

    public static TimetableGroupDTO convertToDTO(TimetableGroup group) {
        TimetableGroupDTO dto = new TimetableGroupDTO();

        dto.setId(group.getId());
        dto.setOfficial(group.getOfficial());
        List<TimetableDTO> timetableDTOS = new ArrayList<>();
        for (Timetable timetable : group.getTimetables()) {
            timetableDTOS.add(TimetableMapper.convertToDTO(timetable));
        }
        dto.setTimetables(timetableDTOS);

        return dto;
    }
}
