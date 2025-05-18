package com.example.pay.service;

import com.example.pay.domain.Pay;
import com.example.pay.global.UserResponse;
import com.example.pay.request.PayRequest;
import com.example.pay.response.PayResponse;

import java.util.List;

public interface PayService {
    PayResponse pay(UserResponse user, PayRequest request, String token);
    PayResponse checkPay(UserResponse user, Long id);
    List<PayResponse> getMyPay(UserResponse user);
}
