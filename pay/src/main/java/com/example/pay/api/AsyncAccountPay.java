//package com.example.pay.api;
//
//import com.example.pay.domain.Pay;
//import com.example.pay.global.UserApi;
//import com.example.pay.repository.PayRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//@RequiredArgsConstructor
//public class AsyncAccountPay {
//    private final PayRepository payRepository;
//    private final UserApi accountApi;
//    @Async
//    public void payAsync(Pay request, String token) {
//        Pay pay = accountApi.pay(user,token, request);
//        payRepository.save(pay);
//    }
//}
