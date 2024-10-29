package com.example.pay.repository;

import com.example.pay.domain.Pay;
import com.example.pay.global.UserResponse;
import com.example.pay.response.PayResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PayRepository
    extends JpaRepository<Pay, Long> {
    List<Pay> findByUserId(String userId);
}
