package org.example.bankingportal.payload;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserRegistrationRequest {

    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String countryCode;

}
