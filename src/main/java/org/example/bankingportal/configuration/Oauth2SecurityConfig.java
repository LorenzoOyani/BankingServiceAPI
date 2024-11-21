package org.example.bankingportal.configuration;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class Oauth2SecurityConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());
        // A redirect back to the client for re-authentication
        http
                .exceptionHandling(exception -> exception
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("https://localhost:8080/login/oauth2/code/my-client"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                // a resource server that accepts access tokens for user info/ client registration
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defualtSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/").permitAll()
                                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout.clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                ).oidcLogout(logout -> logout.backChannel(Customizer.withDefaults()));


        return http.build();

    }

    @Bean
    public LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        logoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/login/logout");
        return logoutSuccessHandler;
    }

    // used to invalidate and remove sessions from memory when logout is complete.
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}

