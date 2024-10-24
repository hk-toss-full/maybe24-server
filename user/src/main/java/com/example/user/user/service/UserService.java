package com.example.user.user.service;

import com.example.user.user.dto.LoginRequest;
import com.example.user.user.dto.RegisterRequest;
import com.example.user.user.entity.User;

public interface UserService {
    public String login(LoginRequest loginRequest);
    public void register(RegisterRequest request);
//    public Boolean checkDuplicate(String id);
}
