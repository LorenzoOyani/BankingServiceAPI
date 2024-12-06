package org.example.bankingportal.controller;

import lombok.AllArgsConstructor;
import org.example.bankingportal.payload.TokenRequest;
import org.example.bankingportal.payload.TokenResponse;
import org.example.bankingportal.service.TokenServices;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController  {

    private final TokenServices tokenService;

    @GetMapping("/createToken")
    public TokenResponse requestToken(@RequestBody TokenRequest tokenRequest) {
        return tokenService.createToken(tokenRequest);
    }
}
