package org.example.bankingportal.payload;

import lombok.*;

@Builder
@Getter
@Setter
public class UserRegistrationRequest {

    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String countryCode;

}
