package com.utcn.project.Model;

import com.utcn.project.Util.Day;

import java.sql.Time;

public class Activity {
    private Long id;
    private String name;
    private Integer nbPeople;
    private Time minTime;
    private Time intervalStart;
    private Time intervalEnd;
    private Qualification qualification;
    private Day day;

}
