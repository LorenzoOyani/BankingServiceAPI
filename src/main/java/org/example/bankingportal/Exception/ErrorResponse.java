package org.example.bankingportal.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {


    private final Instant instant;

    private List<String>  messages;


    public ErrorResponse(String message) {
        this.messages = new ArrayList<>();
        this.messages.add(message);
        this.instant = Instant.now();
    }
}
