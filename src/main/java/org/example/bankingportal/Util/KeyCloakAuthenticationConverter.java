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

        return new JwtAuthenticationToken(jwt); // the implemented contract define by this class
    }

    @SuppressWarnings("unchecked")
    private List<GrantedAuthority> extractJwtTokens(Jwt jwt) {
        Map<String, Object> claims = (Map<String, Object>) jwt.getClaims().get("realm-claims");
        if (claims == null || claims.isEmpty()) {
            return List.of();
        }
        List<String> role = (List<String>) claims.get("authorities");
        if (role == null || role.isEmpty()) {
            role = List.of("ROLE_USER");
        }
        return role.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
