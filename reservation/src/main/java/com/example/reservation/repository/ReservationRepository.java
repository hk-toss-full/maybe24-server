package com.example.reservation.repository;

import com.example.reservation.domain.Reservation;
import com.example.reservation.domain.ReservationType;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(String userId);
    List<Reservation> findByRoundIdAndResStatus(Long roundId, ReservationType resStatus);
}
