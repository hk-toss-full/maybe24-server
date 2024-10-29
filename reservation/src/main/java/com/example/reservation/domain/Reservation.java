package com.example.reservation.domain;

import com.example.reservation.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(
        name = "RESERVATION"
)
// List<String> list = new ArrayList<>;
public class Reservation extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;             // 주문번호 ID
    private Integer ticket_num;             // 티켓 수량
    private LocalDateTime createdAt;        // 예약 날짜

    @Enumerated(EnumType.STRING)
    private ReservationType resStatus;      // SUCCESS: 결제완료, FAILURE: 취소, PENDING: 예약중
    private Long roundId;                   // 각 상품별 회차 ID (사실상 상품 번호)
    private String userId;
}
