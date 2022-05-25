package com.utcn.project;

import static org.junit.jupiter.api.Assertions.*;

import com.utcn.project.Model.Timetable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class TimetableTests {

    @Test
    public void timeTest() {
        LocalTime timeStart = new Time(2, 30, 0).toLocalTime();
        LocalTime timeEnd = new Time(5, 44, 0).toLocalTime();
        LocalTime minTime = new Time(0, 15, 0).toLocalTime();

        LocalTime t1 = timeStart;
        LocalTime t2 = timeStart.plusHours(minTime.getHour());
        t2 = t2.plusMinutes(minTime.getMinute());
        t2 = t2.plusSeconds(minTime.getSecond());

        ArrayList<String> t1s = new ArrayList<>();
        ArrayList<String> t2s = new ArrayList<>();
        while (t2.compareTo(timeEnd) <= 0) {
            t1s.add(t1.toString());
            t2s.add(t2.toString());

            t1 = t1.plusHours(1);
            t2 = t2.plusHours(1);
        }

        assertArrayEquals(new String[]{"02:30", "03:30", "04:30"}, t1s.toArray());
        assertArrayEquals(new String[]{"02:45", "03:45", "04:45"}, t2s.toArray());
    }

}
