package com.example.reservation.controller;

import com.example.reservation.domain.Reservation;
import com.example.reservation.request.ReservationRequest;
import com.example.reservation.response.ReservationResponse;
import com.example.reservation.service.ReservationService;
import com.example.reservation.websocket.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/reserve")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final WebSocketService webSocketService;
    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

//     예약 생성 호출
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = reservationRequest.toEntity();
        Reservation savedReservation = reservationService.createReservation(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    // 예약 확인
    @GetMapping("/{reservationId}/confirm")
    public ResponseEntity<ReservationResponse> confirmReservation(@RequestParam String userId, @PathVariable Long reservationId) {
        ReservationResponse reservationResponse = reservationService.confirmReservation(userId,reservationId);
        return ResponseEntity.ok(reservationResponse);
    }

    // 대기열

    /*
        예약 버튼 클릭 시 사용자 대기열에 추가하거나 바로 예약으로 이동
    */
    @PostMapping("/add")
    public ResponseEntity<String> addToQueueOrReservation(
            @RequestParam String userId) {
        boolean enterReserved = reservationService.addToQueueOrReservation(userId);
        if (enterReserved) {
            return ResponseEntity.ok("예매를 진행합니다.");
        } else {
            return ResponseEntity.ok("예매 대기자에 추가되었습니다.");
        }
    }

    /*
        대기열 상태 확인
    */
    @GetMapping("/status")
    public ResponseEntity<String> getQueueStatus(
            @RequestParam String userId) {
        int position = reservationService.getQueuePosition(userId);
        return ResponseEntity.ok("대기 순번: " + position);
    }

    /*
        결제 페이지로 이동 시 사용자 대기열에서 제거 및 예약 가능 알림
    */
    @PostMapping("/proceed-to-payment")
    public ResponseEntity<String> proceedToPayment(
            @RequestParam String userId) {
        System.out.println("Received userId: " + userId);
        boolean isMoveToPayment = reservationService.proceedToPayment(userId);
        System.out.println("isMoveToPayment : " + isMoveToPayment );

        if (isMoveToPayment) {
            return ResponseEntity.ok("예매 중이던 사용자가 결제 페이지로 이동. 맨 앞 대기자가 예매 가능.");
        } else {
            return ResponseEntity.ok("찾을 수 없는 사용자입니다.");
        }
    }
}


// queueId 로 queue 하나를 만드는거임
