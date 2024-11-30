package org.example.bankingportal.service;

import lombok.RequiredArgsConstructor;
import org.example.bankingportal.payload.TokenResponse;
import org.example.bankingportal.payload.UserRegistrationRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponse authenticate(UserRegistrationRequest registrationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(registrationRequest.getUsername(), registrationRequest.getPassword());
        Authentication authentication =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(authentication.isAuthenticated()){
            String token = null;
            return TokenResponse.builder()
                    .token(null).build();
        }
        throw new BadCredentialsException("Bad credentials");
    }


    @Override
    public boolean register(UserRegistrationRequest registrationRequest) {

        return false;

    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }
}
