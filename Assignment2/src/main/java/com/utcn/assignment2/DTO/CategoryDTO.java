package com.utcn.assignment2.DTO;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
}
