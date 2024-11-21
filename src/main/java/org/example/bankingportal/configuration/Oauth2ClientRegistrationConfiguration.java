package org.example.bankingportal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
public class Oauth2ClientRegistrationConfiguration {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.getClientRegistration());
    }


    private ClientRegistration getClientRegistration() {
        return ClientRegistration.withRegistrationId("Banking-app")
                .clientId("my-client")
                .clientSecret("hL1b2KiesC0DmwyHthDwnLpjxctqNDST")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/keycloak")
                .scope("openid", "read", "write")
                .authorizationUri("http://localhost:8080/realms/my-realm/protocol/openid-connect/auth")
                .tokenUri("http://localhost:8080/realms/my-realm/protocol/openid-connect/token")
                .userInfoUri("http://localhost:8080/realms/my-realm/protocol/openid-connect/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri(" http://localhost:8080/realms/my-realm/protocol/openid-connect/certs")
                .clientName("keycloak")
                .build();
    }
}
