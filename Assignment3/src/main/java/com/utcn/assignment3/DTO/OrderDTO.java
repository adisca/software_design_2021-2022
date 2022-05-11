package com.utcn.assignment3.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;
    private String status;
    private String extraDetails;
    private ClientDTO belongsTo;
    private List<FoodDTO> foods;
}
