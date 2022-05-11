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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "belongsTo")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Client:\n\tid: " + id + "\n\tusername: " + username + "\n\taddress: " + address;
    }
}
