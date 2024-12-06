package org.example.bankingportal.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {


    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-mm-yy")
    private final LocalDateTime instant;

    private final List<String>  messages;


    public ErrorResponse(String message) {
        this.messages = new ArrayList<>();
        this.messages.add(message);
        this.instant = getInstant(); //events occur in real-time
    }
}
