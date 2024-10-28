package com.example.reservation.service;
import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationType;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.response.ReservationResponse;
import com.example.reservation.websocket.WebSocketService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final WebSocketService webSocketService;
    private final ReservationRepository reservationRepository;

    // 예약 생성
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // 예약 확인
    public ReservationResponse confirmReservation(String userId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));

        if (!reservation.getUserId().equals(userId)) {
            System.out.println("userId : " + userId);
            System.out.println("reservation.getUserId() : " + reservation.getUserId());
            throw new IllegalArgumentException("해당 사용자의 예약이 아닙니다.");
        }

        if (reservation.getResStatus() != ReservationType.SUCCESS) {
            System.out.println("reservation.getResStatus() : " + reservation.getResStatus());
            throw new IllegalArgumentException("완료된 예약이 아닙니다.");
        }

        return ReservationResponse.fromJson(reservation);
    }

    // 대기열

    private static final int MAX_CAPACITY = 100;

    /*
        사용자를 대기열에 추가하거나 예약 대기 리스트로 바로 이동시키는 로직
    */
    public void addToQueueOrReservation(String userId, Long eventId) {
        String queueKey = "queue:" + eventId;
        String capacityListKey = "capacity:" + eventId;
        // 수용 인원 리스트가 꽉 찼는지 확인
        Long capacityCount = redisTemplate.opsForList().size(capacityListKey);
        if (capacityCount != null && capacityCount < MAX_CAPACITY) {
            // 수용인원 리스트에 추가 후 바로 예약 진행
            redisTemplate.opsForList().rightPush(capacityListKey, userId);
            webSocketService.sendMessage(userId, "바로 예약 진행이 가능합니다.");
        } else {
            // 대기열에 추가
            long timestamp = System.currentTimeMillis();
            redisTemplate.opsForZSet().add(queueKey, userId, timestamp);
            sendQueuePositionUpdate(userId, eventId);
        }
    }

    /*
        대기열 위치를 실시간으로 업데이트하여 클라이언트에 전송
    */
    private void sendQueuePositionUpdate(String userId, Long eventId) {
    String queueKey = "queue:" + eventId;
    Long rank = redisTemplate.opsForZSet().rank(queueKey, userId);
        if (rank != null) {
            int position = rank.intValue() + 1; // 0-based index 보정
            webSocketService.sendMessage(userId, "현재 대기 순번 : " + position);
        }
    }
    /*
        결제 페이지로 이동 시 사용자 대기열에서 제거하고 다음 사용자에게 예약 가능 알림
    */
    public void proceedToPayment(String userId, Long eventId) {
        String queueKey = "queue:" + eventId;
        String capacityListKey = "capacity:" + eventId;
        // 수용 인원 리스트에서 사용자 제거
        redisTemplate.opsForList().remove(capacityListKey, 1, userId);
        // 다음 사용자 대기열에서 수용 인원 리스트로 이동
        Set<Object> nextUser = redisTemplate.opsForZSet().range(queueKey, 0, 0);
        if (nextUser != null && !nextUser.isEmpty()) {
            String nextUserId = (String) nextUser.iterator().next();
            redisTemplate.opsForZSet().remove(queueKey, nextUserId);
            redisTemplate.opsForList().rightPush(capacityListKey, nextUserId);
            webSocketService.sendMessage(nextUserId, "예매 가능");
        }
    }

    /*
        사용자 대기열 위치 반환
    */
    public int getQueuePosition(String userId, Long eventId) {
        String queueKey = "queue:" + eventId;
        Long rank = redisTemplate.opsForZSet().rank(queueKey, userId);
        return (rank != null) ? rank.intValue() + 1 : -1; // 0-based index 보정
    }

}