package com.utcn.assignment3.Model;

import com.utcn.assignment3.Util.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String extraDetails;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client belongsTo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_food",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods;

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Order:\n\tid: " + id + "\n" + belongsTo.toString() + "\n");
        for (Food food : foods) {
            out.append(food.toString()).append("\n");
        }
        out.append("\n\tTotal: ").append(getTotalPrice());
        out.append("\n\tDetails: ").append(extraDetails).append("\n");
        return out.toString();
    }

    public Float getTotalPrice() {
        Float result = 0f;
        for (Food food : foods) {
            result += food.getPrice();
        }
        return result;
    }
}
