package com.fincore.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fincore.entity.Account;
import com.fincore.entity.Transaction;
import com.fincore.entity.TransactionStatus;
import com.fincore.entity.TransactionType;
import com.fincore.entity.User;
import com.fincore.repository.AccountRepository;
import com.fincore.repository.TransactionRepository;
import com.fincore.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service 
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;

    
    public AccountServiceImpl(AccountRepository accountRepository,UserRepository userRepository,TransactionRepository transactionRepository)
    {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository=transactionRepository;
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

    @Override
    public List<Account> getAllAccounts() {

        return accountRepository.findAll();
        
    }

    @Override
    @Transactional
    public void deposit(Long accountId, BigDecimal amount){

        if(amount==null || amount.compareTo(BigDecimal.ZERO)<0)
        {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        Account account = accountRepository.findById(accountId).
                        orElseThrow(()->new IllegalArgumentException("Account not found"));

        
        if(!"Active".equals(account.getStatus()))
        {
            throw new IllegalArgumentException("Account is not active");
        }

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        
        Transaction transaction = new Transaction();

        transaction.setSourceAccount(account);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);

    }

    @Override
    @Transactional 
    public void withdraw(Long accountId, BigDecimal amount) {

        if(amount==null || amount.compareTo(BigDecimal.ZERO)<0)
        {
            throw new IllegalArgumentException("Withdrwal amount must be greater than zero");
        }

        Account account = accountRepository.findById(accountId).
                        orElseThrow(()->new IllegalArgumentException("Account not found"));

        
        if(!"Active".equals(account.getStatus()))
        {
            throw new IllegalArgumentException("Account is not active");
        }

        if(account.getBalance().compareTo(amount)<0)
        {
            throw new IllegalArgumentException("Insufficient Balance");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        
        Transaction transaction = new Transaction();

        transaction.setSourceAccount(account);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);

    }



        
}

    

    

  

    

