package com.utcn.project.Model;

import com.utcn.project.Model.Enums.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Timetable> timetables;

}
