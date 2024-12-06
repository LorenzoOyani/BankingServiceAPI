package org.example.bankingportal.service;

import org.example.bankingportal.entities.Account;
import org.example.bankingportal.payload.AccountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    /**
     * Creates a new account for a user.
     *
     * @param accountDTO   The  account DTO for separation of concern.

     * @return The created account.
     */
    Account createAccount(AccountDTO accountDTO);

    /**
     * Finds an account by its unique account number.
     *
     * @param accountId The accountId to search for.
     * @return The account with the given account number, or null if not found.
     */
    AccountDTO findByAccountId(long accountId);

    /**
     * Retrieves all accounts associated with a user.
     *
     * @param userId The ID of the user.
     * @return A list of accounts belonging to the user.
     */
    List<AccountDTO> findAccountsByUserId(Long userId);

    /**
     * Updates the status of an account.
     *
     * @param accountId The ID of the account to update.
     * @param status    The new status to set.
     * @return The updated account.
     */
    Account updateAccountStatus(Long accountId, String status);

    /**
     * Updates the available balance of an account.
     *
     * @param accountId The ID of the account.
     * @param amount    The amount to update the balance by.
     * @return The updated account.
     */
    Account updateAccountBalance(Long accountId, BigDecimal amount);

    /**
     * Deletes an account by its ID.
     *
     * @param accountId The ID of the account to delete.
     */
    void deleteAccount(Long accountId);



    /**
     * Closes the account with the specified account number.
     *
     * @param accountNumber The account number of the account to be closed.
     * @return The response indicating the result of the account closure.
     */
    Account closeAccount(String accountNumber);

}
