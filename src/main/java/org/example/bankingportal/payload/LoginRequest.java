package org.example.bankingportal.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotNull
    @Size(min = 2, max = 20)
    private String username;

    @NotNull
    @Size(min = 2, max = 20)
    private String password;


}
