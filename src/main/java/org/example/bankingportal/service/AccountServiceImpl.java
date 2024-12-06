package org.example.bankingportal.service;

import lombok.AllArgsConstructor;
import org.example.bankingportal.Exception.ResourceNotFoundException;
import org.example.bankingportal.Util.AccountNumberGenerator;
import org.example.bankingportal.entities.Account;
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

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private final UserService userService;
    private final AccountMapper accountMapper;
    private final UserMappers userMappers;

    @Override
    public Account createAccount(AccountDTO account) {

        ResponseEntity<UserDTO> users = userService.getUserById(account.getId());
        if (ObjectUtils.isEmpty(users.getBody())) {
            throw new UsernameNotFoundException("user not found");
        }
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


    @Override
    public List<AccountDTO> findAccountsByUserId(Long userId) {
        Account account = accountRepository.findAccountById(userId).orElseThrow(() -> new RuntimeException("account not found"));

        AccountDTO accountDTO = accountMapper.convertFromEntity(account);

        return List.of(accountDTO);
    }

    @Override
    public Account updateAccountStatus(Long accountId, String status) {

        return null;

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
        return null;
    }
}