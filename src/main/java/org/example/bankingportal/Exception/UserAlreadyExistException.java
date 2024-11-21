package org.example.bankingportal.Exception;

public class UserAlreadyExistException extends RuntimeException {



    public UserAlreadyExistException() {
        super(CustomUserError.USER_ALREADY_EXISTS.getMessage());

    }
}
