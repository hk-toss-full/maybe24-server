package com.example.user.account.controller;

import com.example.user.account.dto.AccountResponse;
import com.example.user.account.dto.TransactionRequest;
import com.example.user.account.entity.Account;
import com.example.user.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    @GetMapping
    public AccountResponse getAccount(Authentication authentication) {
        String userId = authentication.getName();
        System.out.println("getAccount, userId: "+userId);
        return AccountResponse.from(accountService.getMyAccount(userId));
    }

    @PostMapping("/save")
   public Account saveAccount(Authentication authentication, @RequestBody TransactionRequest request) {
        return accountService.deposit(authentication.getName(), request);
    }

    @PostMapping("/pay")
    public Account payAccount(Authentication authentication, @RequestBody TransactionRequest request) {
        return accountService.withdrawal(authentication.getName(), request);
    }
}
