package org.example.bankingportal.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class JwtServerResponse {

    private final String accessToken;


    private final String refreshToken;
    private final String tokenType = "Bearer";


    @Setter(AccessLevel.PRIVATE)
    public static class builder {

        String token;
        String refreshToken;


        public JwtServerResponse build() {
            return new JwtServerResponse(token, refreshToken);
        }


    }
}
