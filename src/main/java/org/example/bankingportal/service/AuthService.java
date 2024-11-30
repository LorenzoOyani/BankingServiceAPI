package org.example.bankingportal.service;

import org.example.bankingportal.payload.TokenResponse;
import org.example.bankingportal.payload.UserRegistrationRequest;

public interface AuthService {


    TokenResponse authenticate(UserRegistrationRequest registrationRequest);

    boolean register(UserRegistrationRequest registrationRequest);

    boolean login(String username, String password);


}
