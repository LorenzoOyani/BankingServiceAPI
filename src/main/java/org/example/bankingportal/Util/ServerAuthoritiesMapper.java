package org.example.bankingportal.Util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.*;

public class ServerAuthoritiesMapper implements GrantedAuthoritiesMapper {
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            if (authority instanceof SimpleGrantedAuthority) {
                grantedAuthorities.add(authority);
            } else if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                OidcIdToken oidcIdToken = oidcUserAuthority.getIdToken();
                Map<String, Object> claims = oidcIdToken.getClaims();
                processClaims(grantedAuthorities, claims);
            } else {
                if (authority instanceof OAuth2UserAuthority oAuth2UserAuthority) {
                    Map<String, Object> claims = oAuth2UserAuthority.getAttributes();
                    processClaims(grantedAuthorities, claims);
                }
            }
        }
        return grantedAuthorities;

    }

    @SuppressWarnings("unchecked")
    private void processClaims(Set<GrantedAuthority> grantedAuthorities, Map<String, Object> claims) {
        Map<String, Object> claimsMap = (Map<String, Object>) claims.get("claims");
        if (claimsMap != null && !claimsMap.isEmpty()) {
            List<String> authValues = (List<String>) claimsMap.get("claims");
            authValues.stream()
                    .filter(roles -> roles.startsWith("ROLE_"))
                    .map(SimpleGrantedAuthority::new)
                    .forEach(grantedAuthorities::add);}
    }
}
