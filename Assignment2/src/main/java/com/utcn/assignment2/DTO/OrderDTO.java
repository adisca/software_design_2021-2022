package com.utcn.assignment2.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;
    private String status;
    private ClientDTO belongsTo;
    private List<FoodDTO> foods;
}
