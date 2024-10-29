package com.example.pay.global;


import com.example.pay.domain.Pay;
import com.example.pay.dto.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



// application program interface
@FeignClient("USER")
public interface UserApi {
    @GetMapping("/api/v1/auth/me")
    UserResponse me(@RequestHeader("Authorization") String token);

//    @PostMapping("/account")
//    Pay pay(@RequestHeader("Authorization") String token, @RequestBody Pay pay);

    @PostMapping("/account/pay")
    void pay(@RequestHeader("Authorization") String token, @RequestBody TransactionRequest transactionRequest);
}