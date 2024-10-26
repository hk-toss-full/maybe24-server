package com.example.user.user.service;

import com.example.user.account.entity.Account;
import com.example.user.user.dto.LoginRequest;
import com.example.user.user.dto.RegisterRequest;
import com.example.user.user.entity.User;

import java.util.UUID;

public interface UserService {
    public String login(LoginRequest loginRequest);
    public void register(RegisterRequest request);
    public Boolean checkDup(String id);

}
