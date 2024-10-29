package com.example.reservation.request;

import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationType;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequest(
        Integer ticket_num, String userId, Long roundId
) {
    public Reservation toEntity() {
        return Reservation.builder()
                .ticket_num(ticket_num)
                .createdAt(LocalDateTime.now())
                .resStatus(ReservationType.PENDING)
                .roundId(roundId)
                .userId(userId)
                .build();
    }
}
