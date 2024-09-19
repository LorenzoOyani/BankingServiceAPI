package org.example.bankingportal.methodSecurity;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public  class BankAccountServiceImpl  implements bankService {

    @Override
    public BankAccount findById(int id) {
        return new BankAccount(id, 247991520, "LAWRENCE", 259999.99);
    }

    @Override
    public BankAccount getById(int id) {
        return findById(id);
    }
}
