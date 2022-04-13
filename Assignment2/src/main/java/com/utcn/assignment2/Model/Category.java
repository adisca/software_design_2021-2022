package com.utcn.assignment2.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "belongsTo")
    private List<Food> foods;
    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant belongsTo;
}
