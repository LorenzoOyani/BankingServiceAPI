package org.example.bankingportal.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.bankingportal.entities.AccountStatus;
import org.example.bankingportal.entities.AccountType;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO {

    long id;
    String accountNumber;
    AccountType accountType;
    AccountStatus accountStatus;
    String pin;

    BigDecimal availableBalance;
    UserDTO user;
}


