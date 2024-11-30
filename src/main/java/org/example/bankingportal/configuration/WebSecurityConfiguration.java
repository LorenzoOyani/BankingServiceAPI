package org.example.bankingportal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {


    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails userDetails1 = User.builder()
                .username("John")
                .password("{Noop}")
                .roles("read").build();


   UserDetails userDetails2 = User.builder()
                .username("mikey")
                .password("{Noop}")
                .roles("read").build();

        return new InMemoryUserDetailsManager(List.of(userDetails1, userDetails2));

    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http , UserDetailsService userDetailsService) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .and().build();

    }



}
