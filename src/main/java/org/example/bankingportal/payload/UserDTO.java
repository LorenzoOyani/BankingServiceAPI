package org.example.bankingportal.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    Long id;
    String firstName;


    String lastName;

    String password;

    String email;
}
