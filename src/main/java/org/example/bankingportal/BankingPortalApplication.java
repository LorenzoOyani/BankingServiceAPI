package org.example.bankingportal;

import org.example.bankingportal.methodSecurity.BankAccountServiceImpl;
import org.example.bankingportal.methodSecurity.BankService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableMethodSecurity
public class BankingPortalApplication {

    @Bean
    public BankService bankingService(){
        return new BankAccountServiceImpl();
    }


    public static void main(String[] args) {
        SpringApplication.run(BankingPortalApplication.class, args);
    }

}
