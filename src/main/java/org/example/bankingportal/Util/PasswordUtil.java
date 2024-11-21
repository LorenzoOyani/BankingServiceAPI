package org.example.bankingportal.Util;

import org.passay.*;
import org.passay.LengthRule;
import org.passay.PasswordValidator;

import java.util.List;

public class PasswordUtil {

    public static String passwordValidator(String password) {
        PasswordValidator passwordValidator = new PasswordValidator(List.of(
                new LengthRule(8, 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new CharacterRule(EnglishCharacterData.Alphabetical, 0),
                new WhitespaceRule())
        );

        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return "valid password!";
        } else {
//            return "invalid" + passwordValidator.getMessages(result);
            return String.join(",", "invalid password", passwordValidator.getMessages(result).toString());
        }
    }
}
