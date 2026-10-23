package com.fincore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincore.entity.Account;
import java.util.List;


public interface AccountRepository extends JpaRepository<Account,Long>{

    Optional<Account>findByAccountNumber(String accountNumber);

    List<Account>findByUserId(Long userId);

    boolean existsByAccountNumber(String accountNumber);


    
}
