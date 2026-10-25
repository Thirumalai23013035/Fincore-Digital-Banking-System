package com.fincore.controller;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fincore.dto.DepositRequest;
import com.fincore.dto.TransactionResponse;
import com.fincore.dto.TransferRequest;
import com.fincore.dto.WithdrawalRequest;
import com.fincore.entity.Transaction;
import com.fincore.service.AccountService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

    @PostMapping("/transfer")
    public ResponseEntity<String>transferAmount(@Valid @RequestBody TransferRequest request)
    {
        accountService.transfer(request.getSourceAccountNumber(), request.getTargetAccountNumber(), request.getAmount());

        return ResponseEntity.ok("Amount Successfully transferred");
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<List<TransactionResponse>>getTransactionInformation(@PathVariable("id") Long accountId) {
        
        List<Transaction>transactions = accountService.getTransactionHistory(accountId);

        List<TransactionResponse>response = new ArrayList<>();

        for(Transaction transaction:transactions)
        {
            response.add(TransactionResponse.from(transaction));
        }

        return ResponseEntity.ok(response);
    }
    

    

    
    
}
