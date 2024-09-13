package org.example.bankingportal.service;


import org.example.bankingportal.payload.TokenResponse;
import org.example.bankingportal.payload.UserRegistrationRequest;

public interface AuthService {

    TokenResponse registerUser(UserRegistrationRequest requests);
}
