package com.fincore.service;

import java.math.BigDecimal;
import java.util.List;

import com.fincore.entity.Account;

public interface AccountService {

    Account createAccount(Long userId,String accountType,BigDecimal initialDeposit);

    Account getAccount(Long accountId);

    List<Account>getAllAccounts();

    void deposit(Long accountId,BigDecimal amount);

    void withdraw(Long accountId,BigDecimal amount);
    

    

    
}
