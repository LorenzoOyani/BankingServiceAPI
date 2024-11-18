package org.example.bankingportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.bankingportal.repositories")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableMethodSecurity
public class BankingPortalApplication {


    public static void main(String[] args) {
        SpringApplication.run(BankingPortalApplication.class, args);

    }
}



