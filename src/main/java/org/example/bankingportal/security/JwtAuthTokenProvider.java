package org.example.bankingportal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.example.bankingportal.configuration.ApplicationProperties;
import org.example.bankingportal.configuration.JwtSecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class JwtAuthTokenProvider {

    private final ApplicationProperties properties;

    public Key hmacKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(properties.getSecret().getSecretKey()),
                io.jsonwebtoken.SignatureAlgorithm.HS256.getJcaName());
    }

    public String extractUsername(String bearerToken) {
        return extractClaimBody(bearerToken, Claims::getSubject);
    }

    private <T> T extractClaimBody(String bearerToken, Function<Claims, T> claimsResolver) {

        Jws<Claims> jsClaims = this.extractClaims(bearerToken);

        return claimsResolver.apply(jsClaims.getPayload());

    }

    private Jws<Claims> extractClaims(String bearerToken) {
        return Jwts.parser()
                .setSigningKey(hmacKey())
                .build()
                .parseClaimsJws(bearerToken);
    }

    // validate token with a bearer with the db token!
    public boolean validateToken(String bearerToken, UserDetails userDetails) {
        String username = extractUsername(bearerToken);
        return username.equals(userDetails.getUsername()) && !TokenExpiring(bearerToken);
    }

    private boolean TokenExpiring(String bearerToken) {
        return extractExpiring(bearerToken).before(new Date());
    }

    private Date extractExpiring(String bearerToken) {
        return extractClaimBody(bearerToken, Claims::getExpiration);

    }

    public String generateToken(Map<String, Object> claims, String userDetails) {
        Date createdTime = Date.from(Instant.ofEpochMilli(System.currentTimeMillis() +
                properties.getSecret().getExpirationTime())
        );

        Key signingKey = new SecretKeySpec(
                Base64.getDecoder().decode(properties.getSecret().getSecretKey()),
                io.jsonwebtoken.SignatureAlgorithm.HS256.getJcaName()// Use appropriate algorithm
        );
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails)
                .setIssuedAt(new Date(System.currentTimeMillis() + properties.getSecret().getExpirationTime()))
                .setExpiration(createdTime)
                .signWith(signingKey)
                .compact();

    }
}
