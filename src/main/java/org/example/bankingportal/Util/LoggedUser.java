package org.example.bankingportal.Util;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoggedUser {

    public Map<String, Object> getLoggedInUsers() {
        Map<String, Object> loggedUsers = new ConcurrentHashMap<>();
        AbstractAuthenticationToken authenticationToken =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Jwt jwt = (Jwt) authenticationToken.getPrincipal();

        loggedUsers.put("username", jwt.getClaimAsString("myUser"));
        loggedUsers.put("name", jwt.getClaimAsString(" name"));
        loggedUsers.put("email", jwt.getClaimAsString("email"));
        loggedUsers.put("authorities", AuthorityUtils.authorityListToSet(Collections.synchronizedCollection(authenticationToken.getAuthorities())));
        loggedUsers.put("token", jwt.getTokenValue());
        loggedUsers.put("roles", getRoles(jwt));
        return loggedUsers;
    }


    @SuppressWarnings("unchecked")
    private List<String> getRoles(Jwt jwt) {
        Map<String, Object> realms = (Map<String, Object>) jwt.getClaims().get("real-user");
        if (realms != null && !realms.isEmpty()) {
            for (String s : (List<String>) realms.get("realm-users")) {
                return List.of(s); //Immutable list!
            }

        }
        return List.of();
    }
}
