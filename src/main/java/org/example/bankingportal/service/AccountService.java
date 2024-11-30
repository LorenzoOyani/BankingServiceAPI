package org.example.bankingportal.service;

import org.example.bankingportal.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    /**
     * Creates a new account.
     *
     * @param account The account to create.
     * @return The created account.
     */
    Account createAccount(Account account);

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId The ID of the account to retrieve.
     * @return An Optional containing the account if found, or empty if not.
     */
    Optional<Account> getAccountById(Long accountId);

    /**
     * Retrieves all accounts for a specific user.
     *
     * @param userId The ID of the user whose accounts are to be retrieved.
     * @return A list of accounts belonging to the user.
     */
    List<Account> getAccountsByUserId(Long userId);

    /**
     * Updates the details of an existing account.
     *
     * @param accountId The ID of the account to update.
     * @param updatedAccount The updated account details.
     * @return The updated account.
     */
    Account updateAccount(Long accountId, Account updatedAccount);

    /**
     * Deletes an account by its ID.
     *
     * @param accountId The ID of the account to delete.
     */
    void deleteAccount(Long accountId);

    /**
     * Retrieves an account by its account number.
     *
     * @param accountNumber The unique account number.
     * @return An Optional containing the account if found, or empty if not.
     */
    Optional<Account> getAccountByAccountNumber(String accountNumber);

    /**
     * Retrieves all accounts with a specific status.
     *
     * @param status The account status to filter by.
     * @return A list of accounts with the specified status.
     */
    List<Account> getAccountsByStatus(String status);

//    /**
//     * Deactivates an account by its ID.
//     *
//     * @param accountId The ID of the account to deactivate.
//     * @return The deactivated account.
//     */
//    Account deactivateAccount(Long accountId);
//
//    /**
//     * Transfers funds between two accounts.
//     *
//     * @param fromAccountId The ID of the account to transfer funds from.
//     * @param toAccountId The ID of the account to transfer funds to.
//     * @param amount The amount to transfer.
//     * @return True if the transfer was successful, false otherwise.
//     */
//    boolean transferFunds(Long fromAccountId, Long toAccountId, double amount);
}
