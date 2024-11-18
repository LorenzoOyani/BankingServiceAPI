package org.example.bankingportal.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtSecretKey {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Bean
    public String getSecretKey(){
        return  SingleTonJwtSecretKey.INSTANCE.secretKey;
    }

    private static class SingleTonJwtSecretKey {
        private final static  JwtSecretKey INSTANCE = new JwtSecretKey();
    }
}
