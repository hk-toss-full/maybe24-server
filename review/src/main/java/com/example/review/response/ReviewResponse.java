package com.example.review.response;

import com.example.review.domain.Rating;
import com.example.review.domain.Review;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long reviewId, String author, String content, Rating rating) {

    public static ReviewResponse from (Review review) {
        return new ReviewResponse(
                review.getReviewId(), review.getAuthor(), review.getContent(), review.getRating());
    }

}
