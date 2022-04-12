package com.utcn.assignment2.DTO;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class LogInDTO {
    @NotNull
    private String credential;
    @NotNull
    private String password;
}
