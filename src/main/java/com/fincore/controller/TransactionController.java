package com.fincore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.dto.DepositRequest;
import com.fincore.dto.WithdrawalRequest;
import com.fincore.service.AccountService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/transactions")
public class TransactionController {

    private final AccountService accountService;

    TransactionController(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String>depositAmount(@Valid @RequestBody DepositRequest request)
    {

        accountService.deposit(request.getAccountId(),request.getAmount());

        return ResponseEntity.ok("Deposit Successfull");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String>withdrawAmount(@Valid @RequestBody WithdrawalRequest request)
    {

        accountService.withdraw(request.getAccountId(),request.getAmount());

        return ResponseEntity.ok("Withdrawal Successfull");
    }

    

    
    
}
