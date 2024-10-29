package com.example.user.account.dto;

import com.example.user.account.entity.Account;

public record AccountResponse (
        Long id,
        Long balance
){
    public static AccountResponse from(Account account){
        return new AccountResponse(account.getId(), account.getBalance());
    };
}
