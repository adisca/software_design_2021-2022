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
public class LogInDTO {
    @NotNull
    private String credential;
    @NotNull
    private String password;
}
