package com.example.user.user.controller;

import com.example.user.account.dto.TokenResponse;
import com.example.user.global.Message;
import com.example.user.global.StatusEnum;
import com.example.user.user.dto.CheckDupRequest;
import com.example.user.user.dto.LoginRequest;
import com.example.user.user.dto.RegisterRequest;
import com.example.user.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LoginRequest request) {
            TokenResponse token = new TokenResponse(userServiceImpl.login(request));
            return ResponseEntity.ok(Message.builder().code(200).status(StatusEnum.OK).message("로그인 성공").data(token).build());
    }

    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody RegisterRequest request) {
            userServiceImpl.register(request);
            return ResponseEntity.ok(Message.builder().code(200).status(StatusEnum.OK).message("회원가입되었습니다").build());

    }

    @PostMapping("/check-dup")
    public ResponseEntity<Message> checkDup(@RequestBody CheckDupRequest request) {
    userServiceImpl.checkDup(request.userId());
            return ResponseEntity.ok(Message.builder().code(200).status(StatusEnum.OK).message("사용 가능한 아이디 입니다").build());

    }
}
