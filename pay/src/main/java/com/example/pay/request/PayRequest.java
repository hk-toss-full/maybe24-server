package com.example.pay.request;

import com.example.pay.domain.Pay;
import com.example.pay.domain.PayType;
import com.example.pay.global.UserResponse;

public record PayRequest(
//        Long id, Long targetId, Integer price
        String payId, Integer price
) {
    public Pay toEntity(UserResponse request) {
        return Pay.builder()
                .payId(payId)
                .reservationId(request.getUsername())
                .userId(request.id())
                .price(price)
                .payType(PayType.PENDING)
                .build();
    }
}
