package com.utcn.project.Model;

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
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Time intervalStart;
    private Time intervalEnd;
    private Boolean original;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_activity")
    private Activity activity;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetables_groups",
            joinColumns = @JoinColumn(name = "timetable_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<TimetableGroup> groups;

}
