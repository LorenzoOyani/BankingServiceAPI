package org.example.bankingportal.repositories;

import org.example.bankingportal.entities.Account;
import org.example.bankingportal.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account_id WHERE id=:id", nativeQuery = true)
    Optional<Account> findAccountById(Long id);


    @Query(value = "SELECT *FROM account_id WHERE account_number=:accountNumber", nativeQuery = true)
    Optional<Account> findAccountByAccountNumber(String accountNumber);


    // native query
    @Query(value = "SELECT * FROM  account_id a WHERE a.id = :id AND a.account_type =:accountType", nativeQuery = true)
    Optional<Account> findAccountByIdAndAccountType(@Param("id") Long id, @Param("accountType") AccountType accountType);
}
