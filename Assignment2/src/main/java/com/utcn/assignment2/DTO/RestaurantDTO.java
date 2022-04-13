package com.utcn.assignment2.DTO;

import com.sun.istack.NotNull;
import com.utcn.assignment2.Model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class RestaurantDTO {
    private Long id;
    @NotNull
    private String name;

}
