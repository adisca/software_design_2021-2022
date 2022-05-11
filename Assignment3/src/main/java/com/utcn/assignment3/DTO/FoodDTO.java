package com.utcn.assignment3.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FoodDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private Float price;
}
