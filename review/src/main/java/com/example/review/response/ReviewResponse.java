package com.example.review.response;

import com.example.review.domain.Rating;
import com.example.review.domain.Review;
import lombok.Builder;


import java.time.LocalDateTime;

@Builder
public record ReviewResponse(
        Long reviewId, String author, String content, LocalDateTime createAt ,Rating rating, Long productId) {

    public static ReviewResponse from (Review review) {
        return new ReviewResponse(
                review.getReviewId(), review.getAuthor(), review.getContent(), review.getCreatedAt(), review.getRating(), review.getProductId());
    }

}
