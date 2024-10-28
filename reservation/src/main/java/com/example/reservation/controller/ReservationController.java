package com.example.reservation.controller;

import com.example.reservation.domain.Reservation;
import com.example.reservation.request.ReservationRequest;
import com.example.reservation.response.ReservationResponse;
import com.example.reservation.service.ReservationService;
import com.example.reservation.websocket.WebSocketService;
import java.util.UUID;
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
            @RequestParam String userId,
            @RequestParam Long eventId) {
        reservationService.addToQueueOrReservation(userId, eventId);
        return ResponseEntity.ok("User added to queue or reservation list successfully.");
    }
    /*
        대기열 상태 확인
    */
    @GetMapping("/status")
    public ResponseEntity<String> getQueueStatus(
            @RequestParam String userId,
            @RequestParam Long eventId) {
        int position = reservationService.getQueuePosition(userId, eventId);
        return ResponseEntity.ok("Your position in queue: " + position);
    }

    /*
        결제 페이지로 이동 시 사용자 대기열에서 제거 및 예약 가능 알림
    */
    @PostMapping("/proceed-to-payment")
    public ResponseEntity<String> proceedToPayment(
            @RequestParam String userId,
            @RequestParam Long eventId) {
        reservationService.proceedToPayment(userId, eventId);
        return ResponseEntity.ok("User removed from queue for payment.");
    }


}