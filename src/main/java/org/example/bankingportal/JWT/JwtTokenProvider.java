package org.example.bankingportal.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.configuration.JwtSecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {




    @Autowired
    private final JwtSecretKey jwtSecretKey;


    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey.getSecretKey());

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String authHeader) {

        // remove the prefix 'BEARER' from the  token
        String token = authHeader == null ? null : authHeader.replace("Bearer", "");

        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token");
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT token");
        } catch (IllegalArgumentException e) {
            log.error("Invalid JWT token");
        }
        return false;

    }

    public Long generateTokenId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
}
