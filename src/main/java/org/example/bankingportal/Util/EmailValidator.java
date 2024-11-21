package org.example.bankingportal.Util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_VALIDATOR_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final Pattern pattern = Pattern.compile(EMAIL_VALIDATOR_REGEX);


    public static boolean isValidEmail(String email) {
        if (matchedPattern(email)) {
            return true;
        } else if (matchedDomain(email)) {
            return true;

        } else return isWellFormed(email);
    }

    private static boolean isWellFormed(String email) {
        //More  implementation details later!
            return true;
    }

    private static boolean matchedDomain(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        return pattern.matcher(domain).matches();
    }

    private static boolean matchedPattern(String email) {
        return pattern.matcher(email).matches();
    }


    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return isValidEmail(email);
    }
}
