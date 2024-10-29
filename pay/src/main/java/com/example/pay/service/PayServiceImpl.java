package com.example.pay.service;

//import com.example.pay.api.AsyncAccountPay;
import com.example.pay.domain.Pay;
import com.example.pay.global.UserApi;
import com.example.pay.global.UserResponse;
import com.example.pay.repository.PayRepository;
import com.example.pay.request.PayRequest;
import com.example.pay.response.PayResponse;
import com.example.pay.dto.TransactionRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService{
    private final PayRepository payRepository;
//    private final AsyncAccountPay asyncAccountPay;
    private final UserApi userApi;

    @Transactional
    @Override
    public PayResponse pay(UserResponse user, PayRequest request, String token) {
        // Pay 엔티티 생성 및 저장
        Pay entity = request.toEntity(user);
        payRepository.save(entity);

        // TransactionRequest 생성 및 user 서비스 호출
        TransactionRequest transactionRequest = new TransactionRequest(user.id(), entity.getPrice());
        userApi.pay(token, transactionRequest);

        return PayResponse.from(entity);
    }


//    @Override
//    public PayResponse pay(UserResponse user, PayRequest request, String token) {
//        Pay entity = request.toEntity(user);
//        payRepository.save(entity);
//        asyncAccountPay.payAsync(entity, token);
//        return PayResponse.from(entity);
//    }

    @Override
    public PayResponse checkPay(UserResponse user, Long id) {
        Pay pay = payRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pay not found"));
        if(!pay.getUserId().equals(user.id())) throw new RuntimeException("Not your pay");
        return PayResponse.from(pay);
    }

    @Override
    public List<PayResponse> getMyPay(UserResponse user) {
        return payRepository.findByUserId(user.id())
                .stream()
                .map(PayResponse::from)
                .toList();
    }

}
