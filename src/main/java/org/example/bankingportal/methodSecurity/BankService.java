package org.example.bankingportal.methodSecurity;

public interface BankService {

    @PostReadBankAccounts
    BankAccount findById(int id);

    @PostReadBankAccounts
    BankAccount getById(int id);
}
