package org.example.bankingportal.Exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomUserError {


    USER_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR,   "user already exist");

    private final HttpStatus status;
    private final String message;
    CustomUserError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
