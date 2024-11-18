package org.example.bankingportal.payload;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.bankingportal.Util.ValidEmail;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    @Valid
    @Size(min = 1, max = 50)
    String name;

    @ValidEmail
    @Size(min = 1, max = 50)
    String email;

    @NotNull
    String password;

    @NotNull
    String address;

    @NotNull
    String countryCode;
}
