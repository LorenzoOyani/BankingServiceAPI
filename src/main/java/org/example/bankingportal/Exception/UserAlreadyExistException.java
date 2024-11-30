package org.example.bankingportal.Exception;

public class UserAlreadyExistException extends RuntimeException {


    private String message;

    public UserAlreadyExistException(String message) {
        super(CustomUserError.USER_ALREADY_EXISTS.getMessage());

        this.message = message;
    }


}
