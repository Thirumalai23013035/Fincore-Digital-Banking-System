// package com.fincore.controller;

// import com.fincore.entity.Account;
// import com.fincore.entity.User;
// import com.fincore.repository.UserRepository;
// import com.fincore.service.AccountService;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     private final AccountService accountservice;

//     UserController(AccountService accountService)
//     {
//         this.accountservice = accountService;
//     }

//     @GetMapping
//     public ResponseEntity<List<User>>getAllAccounts() 
//     {
//         return ResponseEntity.ok(accountservice.getAllAccounts());
//     }

   
// }