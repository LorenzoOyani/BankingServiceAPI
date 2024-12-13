package org.example.bankingportal.service;

import org.example.bankingportal.entities.Account;
import org.example.bankingportal.entities.AccountStatus;
import org.example.bankingportal.entities.AccountType;
import org.example.bankingportal.payload.AccountDTO;
import org.example.bankingportal.payload.UserDTO;
import org.example.bankingportal.repositories.AccountRepository;
import org.example.bankingportal.repositories.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@SpringBootTest
class AccountServiceImplTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");


    @MockBean
    private AccountService accountService;

    @MockBean
    private UserService userService;

    @Autowired // interact with a real-database
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    @BeforeAll
    static void setUp() {
        postgres.start();
        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    void createAccount() {
        UserDTO user = new UserDTO(
                1L, "John", "doe", "123", "jd@gmail.com"
        );
        Mockito.when(userService.getUserById(1L)).thenReturn(ResponseEntity.ok(user));
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setAccountStatus(AccountStatus.PENDING);
        accountDTO.setAvailableBalance(BigDecimal.valueOf(5000));
        accountDTO.setPin("1234");

        Account account = accountService.createAccount(accountDTO);

        //validate result;
        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(1L);
        assertThat(account.getAccountType()).isEqualTo(AccountType.SAVINGS);
        assertThat(account.getAvailableBalance()).isEqualTo(BigDecimal.valueOf(5000));
        Account accountDb = accountRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        assertThat(accountDb.getAccountStatus()).isEqualTo(AccountStatus.ACTIVE);
        assertThat(accountDb.getPin()).isEqualTo("1234");
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