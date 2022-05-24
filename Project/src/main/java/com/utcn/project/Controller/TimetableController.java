package com.utcn.project.Controller;

import com.utcn.project.DTO.TimetableDTO;
import com.utcn.project.DTO.TimetableGroupDTO;
import com.utcn.project.Mapper.TimetableGroupMapper;
import com.utcn.project.Mapper.TimetableMapper;
import com.utcn.project.Model.Activity;
import com.utcn.project.Model.User;
import com.utcn.project.Service.ActivityService;
import com.utcn.project.Service.TimetableService;
import com.utcn.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class TimetableController {
    @Autowired
    private TimetableService service;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @PostMapping("/user/{id}/choose")
    @ResponseBody
    public Map<String, Boolean> addUserChoice(@PathVariable Long id, @RequestBody TimetableDTO dto) {
        User user = userService.getById(dto.getUser().getId());
        if (user == null)
            return Collections.singletonMap("status", false);
        Activity activity = activityService.getById(dto.getActivity().getId());
        if (activity == null)
            return Collections.singletonMap("status", false);
        return Collections.singletonMap(
                "status", service.createTimetable(TimetableMapper.convertFromDTO(dto, user, activity)));
    }

    @PostMapping("/admin/timetables/generate")
    public void generateGroups() {
        service.generateTimetables();
        service.generateGroups();
    }

    @GetMapping("/admin/timetables")
    @ResponseBody
    public List<TimetableGroupDTO> getAllGroups() {
        return TimetableGroupMapper.convertToDTOList(service.getGroups());
    }

    @PostMapping("/admin/timetables/pick/{id}")
    public void changeOfficial(@PathVariable Long id) {
        service.changeOfficialGroup(id);
    }

}
