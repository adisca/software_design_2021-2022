package com.utcn.project.Model;

import com.utcn.project.Model.Enums.Day;
import com.utcn.project.Model.Enums.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer nbPeople;
    private Time minTime;
    private Time intervalStart;
    private Time intervalEnd;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @Enumerated(EnumType.STRING)
    private Day day;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private List<Timetable> timetables;

    public Integer getTotalPeople() {
        int total = 0;
        for (Timetable timetable : timetables) {
            if (timetable.getOriginal())
                total += 1;
        }
        return total;
    }

    public Boolean isFull() {
        return nbPeople <= getTotalPeople();
    }
}
