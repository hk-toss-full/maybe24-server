package com.example.review.service;

import com.example.review.domain.Review;
import com.example.review.response.ReviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<ReviewResponse> getAll();
    ReviewResponse saveReviews(Review review);
    List<ReviewResponse> getReviewsByProductId(Long productId);
    void deleteReviews(long reviewId);
    ReviewResponse updateReviews(long reviewId, Review review);
    double averageRating(long productId);
}
