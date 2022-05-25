package com.utcn.project.Controller;

import com.utcn.project.DTO.ActivityDTO;
import com.utcn.project.Mapper.ActivityMapper;
import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Enums.Day;
import com.utcn.project.Model.Enums.Qualification;
import com.utcn.project.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ActivityController {
    @Autowired
    private ActivityService service;

    @GetMapping("/admin/activity")
    @ResponseBody
    public List<ActivityDTO> getAllActivities() {
        return ActivityMapper.convertToDTOList(service.getAll());
    }

    @PostMapping("/admin/activity")
    @ResponseBody
    public Map<String, Boolean> createActivity(@RequestBody ActivityDTO dto) {
        return Collections.singletonMap("status", service.create(ActivityMapper.convertFromDTO(dto)));
    }

    @PostMapping("/user/activity/get_by_qualification")
    @ResponseBody
    public List<ActivityDTO> getActivitiesByQualification(@RequestBody ActivityDTO activityDTO) {
        List<Activity> activities = service.getFiltered(null, null,
                Qualification.valueOf(activityDTO.getQualification()));
        activities.addAll(service.getFiltered(null, null, null));
        return ActivityMapper.convertToDTOList(activities);
    }

    @PostMapping("/user/activity/get_filtered")
    @ResponseBody
    public List<ActivityDTO> getActivitiesFiltered(@RequestBody ActivityDTO activityDTO) {
        List<Activity> activities = service.getFiltered(activityDTO.getName(),
                activityDTO.getDay() == null ? null : Day.valueOf(activityDTO.getDay()),
                Qualification.valueOf(activityDTO.getQualification()));
        activities.addAll(service.getFiltered(activityDTO.getName(),
                activityDTO.getDay() == null ? null : Day.valueOf(activityDTO.getDay()),
                null));
        return ActivityMapper.convertToDTOList(activities);
    }

}
