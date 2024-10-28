package com.example.user.user.service;

import com.example.user.user.dto.LoginRequest;
import com.example.user.user.dto.RegisterRequest;

public interface UserService {
    String login(LoginRequest loginRequest);
    void register(RegisterRequest request);
    Boolean checkDup(String id);

}
