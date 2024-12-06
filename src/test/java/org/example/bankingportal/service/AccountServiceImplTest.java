package org.example.bankingportal.service;

import org.example.bankingportal.repositories.AccountRepository;
import org.example.bankingportal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AccountServiceImplTest {

    @Mock
    private AccountService accountService;

    @Mock
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createAccount() {
    }

    @Test
    void findByAccountId() {
    }

    @Test
    void findAccountsByUserId() {
    }

    @Test
    void updateAccountStatus() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void closeAccount() {
    }
}