package com.example.pay.response;

import com.example.pay.domain.Pay;
import com.example.pay.domain.PayType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record PayResponse(
//        Long id,
//        Integer price,
//        String myAccountName,
//        String targetAccountName,
//        PayType payType
        String payId,
        Integer price,
        LocalDateTime createdAt,
        PayType payType,
        String accountId,
        String reservationId
) {
    public static PayResponse from(Pay pay){
        return new PayResponse(
                pay.getPayId(), pay.getPrice(), pay.getCreatedAt(),
                pay.getPayType(), pay.getUserId() ,pay.getReservationId()
//
//                pay.getId(), pay.getPrice(), pay.getAccountName(),
//                pay.getTargetAccountName(), pay.getPayType()
        );
    }
}
