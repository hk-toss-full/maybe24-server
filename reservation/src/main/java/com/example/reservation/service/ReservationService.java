package com.example.reservation.service;
import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationType;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.response.ReservationResponse;
import com.example.reservation.websocket.WebSocketService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final WebSocketService webSocketService;
    private final ReservationRepository reservationRepository;
    private static final int MAX_CAPACITY = 3;


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

    private static final String CAPACITY_LIST_KEY = "fixed:capacityQueue";
    private static final String QUEUE_KEY = "fixed:waitingQueue";

    /*
        사용자를 대기열에 추가하거나 예약 대기 리스트로 바로 이동시키는 로직
    */
    public boolean addToQueueOrReservation(String userId) {
//        String queueKey = "queue:" + queueId;
//        String capacityListKey = "capacity:" + queueId;
        // 수용 인원 리스트가 꽉 찼는지 확인
        Long capacityCount = redisTemplate.opsForZSet().size(CAPACITY_LIST_KEY);
//        Long capacityCount = redisTemplate.opsForList().size(capacityListKey);
        if (capacityCount != null && capacityCount < MAX_CAPACITY) {
            // 수용인원 queue 에 추가
            long timestamp = System.currentTimeMillis();
            redisTemplate.opsForZSet().add(CAPACITY_LIST_KEY, userId, timestamp);
//            redisTemplate.opsForList().rightPush(CAPACITY_LIST_KEY, userId);
            webSocketService.sendMessage(userId, "바로 예약 진행이 가능합니다.");
            return true;
        } else {
            // 대기열에 추가
            long timestamp = System.currentTimeMillis();
            redisTemplate.opsForZSet().add(QUEUE_KEY, userId, timestamp);
            sendQueuePositionUpdate(userId);
            return false;
        }
    }

    /*
        사용자 대기열 위치 반환
    */
    public int getQueuePosition(String userId) {
//        String queueKey = "queue:" + eventId;
        Long rank = redisTemplate.opsForZSet().rank(QUEUE_KEY, userId);
        if (rank != null) {
            return rank.intValue() + 1;
        }
        return -1;
    }

    //    /*
//        대기열 위치를 실시간으로 업데이트하여 클라이언트에 전송
//    */
    private void sendQueuePositionUpdate(String userId) {
        String queueKey = "queue:" + QUEUE_KEY;
        Long rank = redisTemplate.opsForZSet().rank(queueKey, userId);
        if (rank != null) {
            int position = rank.intValue() + 1; // 0-based index 보정
            webSocketService.sendMessage(userId, "현재 대기 순번 : " + position);
        }
    }


    /*
        결제 페이지로 이동 시 사용자 대기열에서 제거하고 다음 사용자에게 예약 가능 알림
    */
    public boolean proceedToPayment(String userId) {
        // capacityQueue 에 userId 가 있는지 확인하고
        // 해당 user 를 capacityQueue 에서 제거

//        Set<Object> allUsers = redisTemplate.opsForZSet().range(QUEUE_KEY, 0, -1);
//        System.out.println("All users in capacity queue: " + allUsers);
//
//        System.out.println(redisTemplate.hasKey(CAPACITY_LIST_KEY));
//        Long size = redisTemplate.opsForList().size(CAPACITY_LIST_KEY);
//        System.out.println(size);
//        List<Object> range = redisTemplate.opsForList().range(CAPACITY_LIST_KEY, 0, -1);
//        System.out.println(range);
//        List<Object> list = redisTemplate.opsForList().range(CAPACITY_LIST_KEY, 0, -1);
//        int index = -1;
//        for (int i = 0; i < list.size(); i++) {
//            if (Objects.equals(userId, list.get(i))){
//             index = i;
//             break;
//            }
//        }
        Long rank = redisTemplate.opsForZSet().rank(CAPACITY_LIST_KEY, userId);
        if (rank != null) {
            // user 제거
//            System.out.println("222222");
            redisTemplate.opsForZSet().remove(CAPACITY_LIST_KEY, userId);
            // waitingQueue 의 가장 우선 순위 user 를 찾고
            Set<Object> highestUser = redisTemplate.opsForZSet().range(QUEUE_KEY, 0, 0);

            // 결과가 null이 아니고 비어있지 않다면 첫 번째 사용자 ID를 반환
            if (highestUser != null && !highestUser.isEmpty()) {
                System.out.println("33333333");
                String firstUser = (String) highestUser.iterator().next();
                // waitingQueue 에서 제거하고 capacityQueue 로 이동
                redisTemplate.opsForZSet().remove(QUEUE_KEY, firstUser);
                redisTemplate.opsForZSet().remove(CAPACITY_LIST_KEY, userId);
            } else {
                System.out.println("대기자가 존재하지 않습니다.");
            }
            return true;

        } else {
            return false;
        }
    }
}