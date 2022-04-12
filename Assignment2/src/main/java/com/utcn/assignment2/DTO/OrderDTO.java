package com.utcn.assignment2.DTO;

import com.sun.istack.NotNull;
import com.utcn.assignment2.Model.Client;
import com.utcn.assignment2.Model.Food;
import com.utcn.assignment2.Util.OrderStatus;

import javax.persistence.*;
import java.util.List;

public class OrderDTO {

    private Long id;
    private OrderStatus status;
    @NotNull
    private ClientDTO belongsTo;
    private List<FoodDTO> foods;
}
