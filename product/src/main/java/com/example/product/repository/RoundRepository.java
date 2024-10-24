package com.example.product.repository;

import com.example.product.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
    Optional<Round> findByRoundId(Long roundId);
    Optional<Round> findByProductId(Long productId);
}
