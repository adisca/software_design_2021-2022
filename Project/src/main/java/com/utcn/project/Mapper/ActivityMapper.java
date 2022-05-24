package com.utcn.project.Mapper;

import com.utcn.project.DTO.ActivityDTO;
import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Enums.Day;
import com.utcn.project.Model.Enums.Qualification;

import java.util.ArrayList;
import java.util.List;

public final class ActivityMapper {

    private ActivityMapper() {}

    public static List<ActivityDTO> convertToDTOList(List<Activity> activities) {
        ArrayList<ActivityDTO> dtos = new ArrayList<>();
        for (Activity activity : activities) {
            dtos.add(convertToDTO(activity));
        }
        return dtos;
    }

    public static Activity convertFromDTO(ActivityDTO dto) {
        Activity activity = new Activity();

        activity.setQualification(dto.getQualification() == null ? null : Qualification.valueOf(dto.getQualification()));
        activity.setDay(dto.getDay() == null ? null : Day.valueOf(dto.getDay()));
        activity.setIntervalStart(dto.getIntervalStart());
        activity.setIntervalEnd(dto.getIntervalEnd());
        activity.setMinTime(dto.getMinTime());
        activity.setName(dto.getName());
        activity.setNbPeople(dto.getNbPeople());

        return activity;
    }

    public static ActivityDTO convertToDTO(Activity activity) {
        ActivityDTO dto = new ActivityDTO();

        dto.setId(activity.getId());
        dto.setQualification(activity.getQualification() == null ? null : activity.getQualification().toString());
        dto.setDay(activity.getDay() == null ? null : activity.getDay().toString());
        dto.setIntervalStart(activity.getIntervalStart());
        dto.setIntervalEnd(activity.getIntervalEnd());
        dto.setMinTime(activity.getMinTime());
        dto.setName(activity.getName());
        dto.setNbPeople(activity.getNbPeople());

        return dto;
    }
}
