package com.fincore.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fincore.entity.Account;
import com.fincore.entity.User;
import com.fincore.repository.AccountRepository;
import com.fincore.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service 
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    
    public AccountServiceImpl(AccountRepository accountRepository,UserRepository userRepository)
    {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional 
    public Account createAccount(Long userId, String accountType, BigDecimal initialDeposit) {

        if (initialDeposit==null||(initialDeposit.compareTo(BigDecimal.ZERO)<0))
        {
            throw new IllegalArgumentException("Initial deposit should not be a Zero or Negative"); 
        }

        if(accountType==null||!(accountType.equalsIgnoreCase("SAVINGS")||(accountType.equals("CURRENT"))))
        {
            throw new IllegalArgumentException("Account type must me Savings or Current");
        }

        User user = userRepository.findById(userId)
                     .orElseThrow(()->
                        new IllegalArgumentException("User not found"));

        Account account = new Account();

        account.setUser(user);
        account.setAccountNumber(UUID.randomUUID().toString().
        replace("-","").substring(0,16));
        account.setAccountType(accountType.toUpperCase());
        account.setBalance(initialDeposit);
        account.setStatus("Active");

        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long accountId) {

        Account account = accountRepository.findById(accountId)
                            .orElseThrow(()-> new IllegalArgumentException("Account Not found"));

        return account;
       
    }


    
    
}
