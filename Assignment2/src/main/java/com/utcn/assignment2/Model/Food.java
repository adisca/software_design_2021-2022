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
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Float price;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category belongsTo;
    @ManyToMany(mappedBy = "foods")
    private List<Order> orders;
}
