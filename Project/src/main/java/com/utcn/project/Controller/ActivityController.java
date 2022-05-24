package com.utcn.project.Controller;

import com.utcn.project.DTO.ActivityDTO;
import com.utcn.project.Mapper.ActivityMapper;
import com.utcn.project.Model.Activity;
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

    @GetMapping("admin/activity")
    @ResponseBody
    public List<ActivityDTO> getAllActivities() {
        return ActivityMapper.convertToDTOList(service.getAll());
    }

    @PostMapping("admin/activity")
    @ResponseBody
    public Map<String, Boolean> createActivity(@RequestBody ActivityDTO dto) {
        return Collections.singletonMap("status", service.create(ActivityMapper.convertFromDTO(dto)));
    }

    @GetMapping("client/activity/get_by_qualification")
    @ResponseBody
    public List<ActivityDTO> getActivitiesByQualification(@RequestBody String qualification) {
        List<Activity> activities = service.getByQualification(Qualification.valueOf(qualification));
        activities.addAll(service.getByQualification(null));
        return ActivityMapper.convertToDTOList(activities);
    }

}
