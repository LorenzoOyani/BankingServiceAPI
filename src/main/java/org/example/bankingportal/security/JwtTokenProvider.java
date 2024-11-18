package org.example.bankingportal.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.configuration.JwtSecretKey;
import org.example.bankingportal.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtSecretKey jwtSecretKey;

    @Value("${secret.token.expiration-time}")
    private static long TOKEN_EXPIRATION_TIME;

    private final static long TOKEN_REFRESH_TIME = Instant.now().getEpochSecond() + 60 *  60  + 12;

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

    public String generateToken(Authentication authentication) {
        User users = (User) authentication.getPrincipal();
        return generateToken(users);
    }

    public String generateToken(User users) {
        return generateTokenClaims(new HashMap<>(), users, TOKEN_EXPIRATION_TIME);
    }

    private String generateTokenClaims(Map<String, Object> claims,
                                       User users,
                                       long tokenExpirationTime
    ) {
        return Jwts.builder()
                .setSubject(users.getEmail())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey.getSecretKey())
                .compact();

    }

    public boolean isTokenValid(String token, User users) {
        String username = extractUsername(token);
        return username.equals(users.getFirstName()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token) {
        return extractUserClaims(token, Claims::getExpiration);
    }

    private String extractUsername(String token) {
        return extractUserClaims(token, Claims::getSubject);
    }

    private <T> T extractUserClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }


    public String getRefreshToken(User newUserObject) {

       return generateTokenClaims(new HashMap<>(), newUserObject, TOKEN_REFRESH_TIME);
    }
}
