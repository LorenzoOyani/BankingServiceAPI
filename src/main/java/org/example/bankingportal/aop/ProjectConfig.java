package org.example.bankingportal.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ProjectConfig {

    @Bean
    public Logging logging() {
        return new Logging();
    }
}
