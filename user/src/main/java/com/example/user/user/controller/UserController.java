package com.example.user.user.controller;

import com.example.user.user.dto.CheckDupRequest;
import com.example.user.user.dto.LoginRequest;
import com.example.user.user.dto.RegisterRequest;
import com.example.user.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static java.rmi.server.LogStream.log;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userServiceImpl.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userServiceImpl.register(request);
    }

    @PostMapping("/check-dup")
    public Boolean checkDup(@RequestBody CheckDupRequest request) {
        log(request.userId());
        return userServiceImpl.checkDup(request.userId());
    }
}
