package com.example.user.user.dto;

import com.example.user.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public record RegisterRequest(
        String userId,
        String password,
        String nickname,
        String phone,
        String address
){
    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .phone(phone)
                .address(address)
                .build();
    }
}