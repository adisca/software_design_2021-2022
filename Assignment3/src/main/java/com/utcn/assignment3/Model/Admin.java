package com.utcn.assignment3.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "belongsTo")
    private List<Restaurant> restaurants;
}
