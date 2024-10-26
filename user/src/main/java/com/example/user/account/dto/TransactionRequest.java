package com.example.user.account.dto;

public record TransactionRequest(
        Long id,
        int amount
){
    public TransactionRequest toEntity(Long id, int amount){
        return new TransactionRequest(id, amount);
    }
}
