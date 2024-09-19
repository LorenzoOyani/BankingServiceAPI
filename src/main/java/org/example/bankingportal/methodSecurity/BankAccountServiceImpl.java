package org.example.bankingportal.methodSecurity;

public class BankAccountServiceImpl implements BankService {

    @Override
    public BankAccount findById(int id) {


        return new BankAccount(id, "247991520", "LAWRENCE", 259999.99);
    }

    @Override
    public BankAccount getById(int id) {
        return findById(id);
    }
}
