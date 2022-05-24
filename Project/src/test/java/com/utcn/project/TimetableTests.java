package com.utcn.project;

import static org.junit.jupiter.api.Assertions.*;

import com.utcn.project.Model.Timetable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
public class TimetableTests {

    @Test
    public void timeTest() {
        LocalTime timeStart = new Time(2, 30, 0).toLocalTime();
        LocalTime timeEnd = new Time(10, 44, 0).toLocalTime();
        LocalTime minTime = new Time(0, 15, 0).toLocalTime();

        LocalTime t1 = timeStart;
        LocalTime t2 = timeStart.plusHours(minTime.getHour());
        t2 = t2.plusMinutes(minTime.getMinute());
        t2 = t2.plusSeconds(minTime.getSecond());

        while (t2.compareTo(timeEnd) <= 0) {
            System.out.println("Time1:" + t1);
            System.out.println("Time2:" + t2);

            t1 = t1.plusHours(1);
            t2 = t2.plusHours(1);
        }
    }

}
