package com.utcn.project.DTO;

import com.utcn.project.Model.Qualification;
import com.utcn.project.Util.Day;

import java.sql.Time;

/**
 * Careful at the DTOs they aren't done properly
 */

public class ActivityDTO {
    private Long id;
    private String name;
    private Integer nbPeople;
    private Time minTime;
    private Time intervalStart;
    private Time intervalEnd;
    private QualificationDTO qualification;
    private Day day;

}
