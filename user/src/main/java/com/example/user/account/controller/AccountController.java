package com.example.user.account.controller;

import com.example.user.account.dto.AccountResponse;
import com.example.user.account.dto.TransactionRequest;
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
        return AccountResponse.from(accountService.getMyAccount(userId));
    }

    @PostMapping("/save")
    public AccountResponse saveAccount(Authentication authentication, @RequestBody TransactionRequest request) {
        System.out.println(request);
        String userId = authentication.getName();
        return AccountResponse.from(accountService.deposit(userId, request));
    }

    @PostMapping("/pay")
    public AccountResponse payAccount(Authentication authentication, @RequestBody TransactionRequest request) {
        String userId = authentication.getName();
        return AccountResponse.from(accountService.withdrawal(userId, request));
    }
}
