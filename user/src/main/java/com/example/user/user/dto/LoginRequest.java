package com.example.user.user.dto;

public record LoginRequest(
        String userId,
        String password
){}