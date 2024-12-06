package org.example.bankingportal.service;

import lombok.AllArgsConstructor;
import org.example.bankingportal.configuration.ApplicationProperties;
import org.example.bankingportal.payload.TokenRequest;
import org.example.bankingportal.payload.TokenResponse;
import org.example.bankingportal.security.AuthUserDetailService;
import org.example.bankingportal.security.JwtAuthTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
public class TokenServices {

    private final AuthenticationManager authenticationManager;

    private final AuthUserDetailService authUserDetailService;


    private final ApplicationProperties properties;

    private final JwtAuthTokenProvider jwtTokenProvider;

    public TokenResponse createToken(TokenRequest tokenRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(tokenRequest.getEmail(), tokenRequest.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(!authentication.isAuthenticated()){
            throw new BadCredentialsException("Invalid username or password");
        }

        UserDetails userDetails = this.authUserDetailService.loadUserByUsername(tokenRequest.getEmail());

        String token = this.jwtTokenProvider.generateToken(Map.of("roles", userDetails.getAuthorities()), userDetails.getUsername());

        return TokenResponse.builder()
                .token(token)
                .instant(Instant.ofEpochMilli(System.currentTimeMillis() + this.properties.getSecret().getExpirationTime()))
                .build();

    }
}
