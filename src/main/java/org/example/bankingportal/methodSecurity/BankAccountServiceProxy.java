package org.example.bankingportal.methodSecurity;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BankAccountServiceProxy implements BankService {


    private final BankAccountServiceImpl delegate;

    public BankAccountServiceProxy(BankAccountServiceImpl delegate) {
        this.delegate = delegate;
    }


    @Override
    public BankAccount findById(int id) {
//        BankAccount bank = super.findById(id);   //Overriding!

        BankAccount bankAccount = delegate.findById(id);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthorizationDeniedException("user not authenticated", new AuthorizationDecision(false));
        }

        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (!bankAccount.getOwner().equals(username)) {
            throw new AuthorizationDeniedException("denied", new AuthorizationDecision(false));
        }

        return bankAccount;
    }

    @Override
    public BankAccount getById(int id) {
        return delegate.getById(id);
    }
}
