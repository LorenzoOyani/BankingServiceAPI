package org.example.bankingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecurityHelper {
    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    public SecurityHelper(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }
    // get auth from the security context
    // check if the auth object is an instance of 0auth2
    //...

    public String getAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2AuthenticationToken oAuth2Token)) {
            return null;
        }
        OAuth2AuthorizedClient clientAuth = authorizedClientService.loadAuthorizedClient(
                oAuth2Token.getAuthorizedClientRegistrationId(), oAuth2Token.getName()
        );
        return clientAuth.getAccessToken().getTokenValue();
    }

    public  Map<String, Object> getLoggedUerDetails() {
        Map<String, Object> details = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            return Collections.emptyMap();
        }

        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = oidcUser.getAuthorities();
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        OidcUserInfo oidcUserInfo = oidcUser.getUserInfo();
        details.put("id", oidcUserInfo.getSubject());
        details.put("username", oidcUserInfo.getEmail());
        details.put("given_name", oidcUserInfo.getGivenName());
        details.put("family_name", oidcUserInfo.getFamilyName());
        details.put("email", oidcUserInfo.getEmail());
        details.put("phone_number", oidcUserInfo.getPhoneNumber());
        details.put("roles", roles);


        return details;
    }
}


