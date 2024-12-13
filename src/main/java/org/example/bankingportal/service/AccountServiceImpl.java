package org.example.bankingportal.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.bankingportal.Exception.ResourceNotFoundException;
import org.example.bankingportal.Util.AccountNumberGenerator;
import org.example.bankingportal.entities.Account;
import org.example.bankingportal.entities.AccountStatus;
import org.example.bankingportal.entities.AccountType;
import org.example.bankingportal.entities.User;
import org.example.bankingportal.mapper.AccountMapper;
import org.example.bankingportal.mapper.UserMappers;
import org.example.bankingportal.payload.AccountDTO;
import org.example.bankingportal.payload.UserDTO;
import org.example.bankingportal.repositories.AccountRepository;
import org.example.bankingportal.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserService userService;

    private final AccountMapper accountMapper;

    @Override
    public Account createAccount(AccountDTO account) {

        ResponseEntity<UserDTO> users = userService.getUserById(account.getId());
        if (ObjectUtils.isEmpty(users.getBody())) { // for logging and debugging
            throw new UsernameNotFoundException("user not found");
        }

        accountRepository.findAccountByIdAndAccountType(account.getId(), AccountType.valueOf(account.getAccountType().toString()))
                .ifPresent(account1 -> {
                    log.info("account already exists");
                    throw new ResourceNotFoundException("account already exists");
                });

        String accountNumber;
        do {
            accountNumber = AccountNumberGenerator.generateAccountNumber();
        } while (accountRepository.findAccountByAccountNumber(accountNumber).isPresent());

        AccountDTO dbAccount = this.findByAccountId(account.getId());
        Account newAccount = accountMapper.convertToEntity(dbAccount);
        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(account.getAccountType());
        newAccount.setAccountStatus(account.getAccountStatus());
        newAccount.setAvailableBalance(account.getAvailableBalance());
        newAccount.setPin(account.getPin());

        return accountRepository.save(newAccount);

    }

    @Override
    public AccountDTO findByAccountId(long accountId) {
        return accountRepository
                .findAccountById(accountId)
                .map(accountMapper::convertFromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("user not in the server!"));
    }


    public AccountDTO readAccountById(Long userId) {
        return accountRepository.findAccountById(userId)
                .map(account -> {
                   if(!account.getAccountStatus().equals(AccountStatus.ACTIVE)) {
                       throw new ResourceNotFoundException("account not activated");
                   }
                   AccountDTO dbAccount = this.accountMapper.convertFromEntity(account);
                   dbAccount.setAccountStatus(account.getAccountStatus());
                   dbAccount.setAccountType(account.getAccountType());
                   return dbAccount;
                }).orElseThrow(() -> new ResourceNotFoundException("user not in the server!"));
    }



    @Override
    public List<AccountDTO> findAccountsByUserId(Long userId) {
        Account account = accountRepository.findAccountById(userId).orElseThrow(() -> new RuntimeException("account not found"));

        AccountDTO accountDTO = accountMapper.convertFromEntity(account);

        return List.of(accountDTO);
    }

    @Override
    public Account updateAccountStatus(Long accountId, String status) {
        return accountRepository.findAccountById(accountId)
                .map(account -> {
                    if (!account.getAccountStatus().equals(AccountStatus.ACTIVE)) {
                        throw new ResourceNotFoundException("account not activated");
                    }

                    if (account.getAvailableBalance().compareTo(BigDecimal.ZERO) < 0 || account.getAvailableBalance().compareTo(BigDecimal.valueOf(1000)) < 0) {
                        throw new RuntimeException("insufficient balance");
                    }
                    account.setAccountStatus(AccountStatus.valueOf(status));
                    return accountRepository.save(account);
                }).orElseThrow(() -> new RuntimeException("account not found in server"));

    }

    @Override
    public Account updateAccountBalance(Long accountId, BigDecimal amount) {
        return null;
    }

    @Override
    public void deleteAccount(Long accountId) {

    }

    @Override
    public Account closeAccount(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber)
                .map(account -> {
                    if (BigDecimal.valueOf(Double.parseDouble(getBalance(accountNumber))).compareTo(BigDecimal.ZERO) != 0) {
                        throw new RuntimeException("Account needs to be zero");
                    }

                    account.setAccountStatus(AccountStatus.CLOSED);
                    return account;
                }).orElseThrow(() -> new RuntimeException("account not found in server"));

    }

    private String getBalance(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber)
                .map(account -> account.getAvailableBalance().toString()).orElseThrow(() -> new RuntimeException("zero balance"));
    }
}
