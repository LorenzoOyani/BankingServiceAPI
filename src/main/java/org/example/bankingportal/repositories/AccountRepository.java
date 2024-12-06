package org.example.bankingportal.repositories;

import org.example.bankingportal.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account_id WHERE id=:id", nativeQuery = true)
    Optional<Account> findAccountById(Long id);


    Optional<String> findAccountByAccountNumber(String accountNumber);
}
