package com.example.user.account.controller;

import com.example.user.account.dto.AccountResponse;
import com.example.user.account.dto.TransactionRequest;
import com.example.user.account.service.AccountService;
import com.example.user.global.Message;
import com.example.user.global.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    @GetMapping
    public ResponseEntity<Message> getAccount(Authentication authentication) {
        String userId = authentication.getName();
            AccountResponse accountResponse = AccountResponse.from(accountService.getMyAccount(userId));
            return ResponseEntity.ok(Message.builder().code(200).status(StatusEnum.OK).message("성공").data(accountResponse).build());



    }

    @PostMapping
    public ResponseEntity<Message> saveAccount(Authentication authentication, @RequestBody TransactionRequest request) {
        String userId = authentication.getName();
            AccountResponse accountResponse = AccountResponse.from(accountService.deposit(userId, request));
            return ResponseEntity.ok(Message
                    .builder()
                    .message("성공적으로 수행되었습니다.")
                    .code(200)
                    .status(StatusEnum.OK)
                    .data(accountResponse)
                    .build());

    }

    @PostMapping("/pay")
    public ResponseEntity<Message> payAccount(Authentication authentication, @RequestBody TransactionRequest request) {
        String userId = authentication.getName();

            AccountResponse accountResponse = AccountResponse.from(accountService.withdrawal(userId, request));
            return ResponseEntity.ok(Message
                    .builder()
                    .message("성공적으로 수행되었습니다.")
                    .code(200)
                    .status(StatusEnum.OK)
                    .data(accountResponse)
                    .build());

    }
}
