package org.example.bankingportal.methodSecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest
class BankAccountServiceTest {


    //Interface based-proxies!

    @Autowired
    private  BankService bankAccountService;


    @WithMockUser("LAWRENCE")
    @Test
    void findUserById() {
        bankAccountService.findById(1);
    }

    @WithMockUser("Josh")
    @Test
    void findById_but_denied() {
        assertThatExceptionOfType(AuthorizationDeniedException.class)
                .isThrownBy(() -> bankAccountService.findById(1));
    }

    @WithMockUser("LAWRENCE")
    @Test
    void getUserById() {
        bankAccountService.getById(1);
    }

    @WithMockUser("josh")
    @Test
    void getUserById_but_fail(){

        assertThatExceptionOfType(AuthorizationDeniedException.class)
                .isThrownBy(()-> bankAccountService.getById(1));
    }

    @WithMockUser(username="accountant", roles = "ACCOUNTANT")
    @Test
    void getByIdByAccountant(){
        bankAccountService.getById(1);
    }

    @WithMockUser(username = "accountant",  roles="ACCOUNTANT")
    @Test
    void findByIdUserAccountByAccountant(){
       assertThat(bankAccountService.findById(1).getAccountNumber()).isEqualTo("****");
    }

    @WithMockUser(username="accountant", roles = "ACCOUNTANT")
    @Test
    void findByIdByAccountant(){
        bankAccountService.findById(1);
    }
}