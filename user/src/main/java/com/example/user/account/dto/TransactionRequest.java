package com.example.user.account.dto;

public record TransactionRequest(
        String userId,
        int amount
){
    public TransactionRequest toEntity(String userId, int amount){
        return new TransactionRequest(userId, amount);
    }
}
