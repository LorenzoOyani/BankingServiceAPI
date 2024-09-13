package org.example.bankingportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankingPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingPortalApplication.class, args);
    }

}
