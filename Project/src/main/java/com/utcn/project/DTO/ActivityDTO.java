package com.utcn.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

/**
 * Careful at the DTOs they aren't done properly
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private Long id;
    private String name;
    private Integer nbPeople;
    private Time minTime;
    private Time intervalStart;
    private Time intervalEnd;
    private String qualification;
    private String day;

}
