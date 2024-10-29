package com.example.pay.domain;


import com.example.pay.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(
        name = "PAY"
)
public class Pay extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String payId;
    private Integer price;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private PayType payType;
    private String userId;
    private String reservationId;


//    private Long id;
//    private Integer price;
//    private Long accountId;
//    private String accountName;
//    private Long targetAccountId;
//    private String targetAccountName;
//    private String userId;
//    private String userName;
}
