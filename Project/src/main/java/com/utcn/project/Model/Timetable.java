package com.utcn.project.Model;

import com.utcn.project.Model.Enums.Day;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_activity")
    private Activity activity;
    @Enumerated(EnumType.STRING)
    private Day day;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "timetable_group",
            joinColumns = @JoinColumn(name = "timetanle_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<TimetableGroup> groups;

}
