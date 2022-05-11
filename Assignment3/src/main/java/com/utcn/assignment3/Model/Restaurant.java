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
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private String zones;
    @ManyToOne
    @JoinColumn(name = "id_admin")
    private Admin belongsTo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "belongsTo")
    private List<Category> menus;
}
