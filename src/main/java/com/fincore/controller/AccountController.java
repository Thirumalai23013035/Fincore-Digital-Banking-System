package com.fincore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.dto.CreateAccountRequest;
import com.fincore.entity.Account;
import com.fincore.service.AccountService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountservice;

    AccountController(AccountService accountService)
    {
        this.accountservice = accountService;
    }

    @PostMapping
    public ResponseEntity<Account>createAccount(@Valid @RequestBody CreateAccountRequest request)
    {
            Account account = accountservice.createAccount(
                request.getUserId(),
                request.getAccountType(), 
                request.getInitialDeposit());

            
            return ResponseEntity.status(HttpStatus.CREATED).body(account);


    }

    @GetMapping("/{id}")
    public ResponseEntity<Account>getaccount(@PathVariable Long id)
    {
        Account account = accountservice.getAccount(id);

        return ResponseEntity.ok(account);
    }
    

    
}
