package com.example.review.repository;

import com.example.review.domain.Review;
import com.example.review.response.ReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

}
