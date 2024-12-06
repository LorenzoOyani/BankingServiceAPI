package org.example.bankingportal.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private final Secret secret = new Secret();

    @Getter
    @Setter
    public static class Secret { // blue-print!
        private String secretKey;
        private long expirationTime;
    }
}
