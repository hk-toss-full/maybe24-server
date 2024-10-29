package com.example.reservation.response;
import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationType;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationResponse (
        Long reservationId,             // 주문번호 ID
        Integer ticket_num,             // 티켓 수량
        LocalDateTime createdAt,        // 예약 날짜
        ReservationType resStatus,     // 1: 결제완료, 2: 취소, 3: 예약중
        Long roundId,                   // 각 상품별 회차 ID (사실상 상품 번호)
        String userId
) {
    public static ReservationResponse fromJson(Reservation reservation) {
        return new ReservationResponse(
                reservation.getReservationId()
                , reservation.getTicket_num()
                , reservation.getCreatedAt()
                , reservation.getResStatus()
                , reservation.getRoundId()
                , reservation.getUserId()
        );
    }


}