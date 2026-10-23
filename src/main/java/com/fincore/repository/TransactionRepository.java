package com.fincore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincore.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

    List<Transaction>findBySourceAccountId(Long accountId);

    List<Transaction>findByTargetAccount(Long accountId);

    
    
}
