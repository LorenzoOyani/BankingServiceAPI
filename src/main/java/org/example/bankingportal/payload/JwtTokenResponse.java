package org.example.bankingportal.payload;

import com.nimbusds.oauth2.sdk.token.RefreshToken;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenResponse {

    private String token;

    private String refreshToken;

    private String email;


}
