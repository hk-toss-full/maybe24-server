package com.example.review.service;

import com.example.review.domain.Review;
import com.example.review.response.ReviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<ReviewResponse> getAll();
    ReviewResponse saveReviews(Review review);
    void deleteReviews(long reviewId);
    ReviewResponse updateReviews(long reviewId, Review review);
}
