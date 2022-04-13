package com.utcn.assignment2.Model;

import com.utcn.assignment2.Util.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client belongsTo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_food",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
