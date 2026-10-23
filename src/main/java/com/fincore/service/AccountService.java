package com.fincore.service;

import java.math.BigDecimal;

import com.fincore.entity.Account;

public interface AccountService {

    Account createAccount(Long userId,String accountType,BigDecimal initialDeposit);

    Account getAccount(Long accountId);
    
}
