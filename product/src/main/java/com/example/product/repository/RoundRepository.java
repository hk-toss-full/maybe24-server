package com.example.product.repository;

import com.example.product.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
    Round findByProductProductId(Long productId);
}
