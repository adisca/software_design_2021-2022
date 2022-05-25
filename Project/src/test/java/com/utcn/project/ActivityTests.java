package com.utcn.project;

import com.utcn.project.Model.Activity;
import com.utcn.project.Model.Enums.Day;
import com.utcn.project.Model.Enums.Qualification;
import com.utcn.project.Repo.ActivityRepo;
import com.utcn.project.Service.ActivityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ActivityTests {

    @Mock
    ActivityRepo activityRepo;

    @Mock
    Activity activity1;
    @Mock
    Activity activity2;
    @Mock
    Activity activity3;
    @Mock
    Activity activity4;
    @Mock
    Activity activity5;
    @Mock
    Activity activity6;
    @Mock
    Activity activity7;
    @Mock
    Activity activity8;
    @Mock
    Activity activity9;

    @Test
    public void filterTest() {
        assertNotNull(activityRepo);

        when(activity1.isFull()).thenReturn(false);
        when(activity2.isFull()).thenReturn(false);
        when(activity3.isFull()).thenReturn(false);
        when(activity4.isFull()).thenReturn(false);
        when(activity5.isFull()).thenReturn(false);
        when(activity6.isFull()).thenReturn(false);
        when(activity7.isFull()).thenReturn(true);
        when(activity8.isFull()).thenReturn(true);
        when(activity9.isFull()).thenReturn(false);

        when(activityRepo.findAllByDayAndQualification(Day.FRIDAY, Qualification.FRONTEND))
                .thenReturn(List.of(activity1, activity7));
        when(activityRepo.findAllByDayAndQualification(null, Qualification.FRONTEND))
                .thenReturn(List.of(activity5));

        when(activityRepo.findAllByNameAndQualification("Party", Qualification.FRONTEND))
                .thenReturn(List.of(activity2));

        when(activityRepo.findAllByNameAndDayAndQualification("Party", Day.FRIDAY, Qualification.FRONTEND))
                .thenReturn(List.of(activity3));
        when(activityRepo.findAllByNameAndDayAndQualification("Party", null, Qualification.FRONTEND))
                .thenReturn(List.of(activity6));

        when(activityRepo.findAllByQualification(null))
                .thenReturn(List.of(activity4, activity8, activity9));

        ActivityService service = new ActivityService(activityRepo);

        assertArrayEquals(new Activity[]{activity1, activity5},
                service.getFiltered(null, Day.FRIDAY, Qualification.FRONTEND).toArray());
        assertArrayEquals(new Activity[]{activity2},
                service.getFiltered("Party", null, Qualification.FRONTEND).toArray());
        assertArrayEquals(new Activity[]{activity3, activity6},
                service.getFiltered("Party", Day.FRIDAY, Qualification.FRONTEND).toArray());
        assertArrayEquals(new Activity[]{activity4, activity9},
                service.getFiltered(null, null, null).toArray());
    }

}
