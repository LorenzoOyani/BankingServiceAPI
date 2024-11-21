package org.example.bankingportal.configuration;

import org.example.bankingportal.Util.KeyCloakAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    @Order(3)
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(HttpMethod.GET, "/messages").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(C -> C.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(new KeyCloakAuthenticationConverter()))
                );
        return http.build();

    }

    ;
}
