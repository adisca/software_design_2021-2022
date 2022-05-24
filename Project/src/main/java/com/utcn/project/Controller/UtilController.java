package com.utcn.project.Controller;

import com.utcn.project.Model.Enums.Day;
import com.utcn.project.Model.Enums.Qualification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UtilController {

    @GetMapping("/util/qualification")
    @ResponseBody
    public List<String> getAllQualifications() {
        List<String> qualificationValues = new ArrayList<>();
        Qualification[] qualifications = Qualification.values();
        for (Qualification qualification : qualifications) {
            qualificationValues.add(qualification.toString());
        }
        return qualificationValues;
    }

    @GetMapping("/util/day")
    @ResponseBody
    public List<String> getAllDays() {
        List<String> dayValues = new ArrayList<>();
        Day[] days = Day.values();
        for (Day day : days) {
            dayValues.add(day.toString());
        }
        return dayValues;
    }

}
