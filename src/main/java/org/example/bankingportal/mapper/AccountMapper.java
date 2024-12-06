package org.example.bankingportal.mapper;

import org.example.bankingportal.Util.BaseEntity;
import org.example.bankingportal.entities.Account;
import org.example.bankingportal.payload.AccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class AccountMapper extends BaseEntity<AccountDTO, Account> {
    @Override
    public Account convertToEntity(AccountDTO accountDTO, Object... args) {
        Account account = new Account();
        if(ObjectUtils.isEmpty(accountDTO)){
            BeanUtils.copyProperties(account,accountDTO);
        }
        return account;
    }

    @Override
    public  AccountDTO convertFromEntity(Account account, Object... args) {
        AccountDTO accountDTO = new AccountDTO();
        if(ObjectUtils.isEmpty(account)){
            BeanUtils.copyProperties(account,accountDTO);
        }
        return accountDTO;

    }
}
