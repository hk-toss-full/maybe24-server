package com.example.pay.controller;

import com.example.pay.domain.Pay;
import com.example.pay.global.UserResponse;
import com.example.pay.request.PayRequest;
import com.example.pay.response.PayResponse;
import com.example.pay.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PayController {
    private final PayService payService;
    @GetMapping("/api/v1/pay")
    public List<PayResponse> getMyPay(
            @AuthenticationPrincipal UserResponse user
            ){
        return payService.getMyPay(user);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/pay")
    public PayResponse postMyPay(
            @AuthenticationPrincipal UserResponse user,
            @RequestBody PayRequest request,
            @RequestHeader("Authorization") String token
            ){
        return payService.pay(user,request, token);
    }
    @GetMapping("/api/v1/pay/{id}")
    public PayResponse getMyPay(
            @AuthenticationPrincipal UserResponse user,
            @PathVariable Long id
        ){
        return payService.checkPay(user, id);
    }
}
