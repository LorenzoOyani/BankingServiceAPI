package org.example.bankingportal.Util;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;

@NonNullApi
public class KeyCloakAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final Converter<Jwt, Collection<GrantedAuthority>> delegate = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        List<GrantedAuthority> authorityList = this.extractJwtTokens(jwt);
        Collection<GrantedAuthority> authorities = delegate.convert(jwt);

        if (authorities != null) {
            authorities.addAll(authorityList);
        }

        return new JwtAuthenticationToken(jwt);
    }

    @SuppressWarnings("unchecked")
    private List<GrantedAuthority> extractJwtTokens(Jwt jwt) {
        Map<String, Object> claims = jwt.hasClaim("claims") ? jwt.getClaims() : Collections.emptyMap(); // reduce the redundancy of an if statement check!
        List<String> storedClaims = (List<String>) claims.get("claims");

        if (storedClaims == null || storedClaims.isEmpty()) {
            storedClaims = List.of("ROLE_ADMIN");
        }

        return storedClaims.stream()
                .filter(role -> role.startsWith("ROLE_"))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }
}
