package org.example.bankingportal.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class TokenResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @Builder.Default
    private Instant instant = Instant.now().plusSeconds(3600);


}
