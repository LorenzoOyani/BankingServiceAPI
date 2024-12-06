package org.example.bankingportal.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {

    private String email;
    private String password;
}
