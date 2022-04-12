package com.utcn.assignment2.DTO;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
