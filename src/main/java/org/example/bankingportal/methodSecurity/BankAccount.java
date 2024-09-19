package org.example.bankingportal.methodSecurity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class BankAccount {
    final int id;
    final String accountNumber;
    final String owner;
    final double balance;

    public BankAccount(int id, String accountNumber, String owner, double balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }


    @PreAuthorize("this.owner  == authentication?.name")
    @HandleAuthorizationDenied(handlerClass = MaskMethodAuthorizationDeniedHandler.class)
    public String getAccountNumber() {
        return accountNumber;
    }

    }