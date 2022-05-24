package com.utcn.project.Mapper;

import com.utcn.project.DTO.TimetableDTO;
import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Timetable;
import com.utcn.project.Model.User;

public final class TimetableMapper {

    private TimetableMapper() {}

    public static Timetable convertFromDTO(TimetableDTO dto, User user, Activity activity) {
        Timetable timetable = new Timetable();

        timetable.setOriginal(true);
        timetable.setIntervalStart(dto.getIntervalStart());
        timetable.setIntervalEnd(dto.getIntervalEnd());
        timetable.setUser(user);
        timetable.setActivity(activity);

        return timetable;
    }

    public static TimetableDTO convertToDTO(Timetable timetable) {
        TimetableDTO dto = new TimetableDTO();

        dto.setId(timetable.getId());
        dto.setIntervalStart(timetable.getIntervalStart());
        dto.setIntervalEnd(timetable.getIntervalEnd());
        dto.setUser(UserMapper.convertToDTO(timetable.getUser()));
        dto.setActivity(ActivityMapper.convertToDTO(timetable.getActivity()));

        return dto;
    }

}
