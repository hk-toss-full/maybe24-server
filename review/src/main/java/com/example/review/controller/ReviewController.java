package com.example.review.controller;

import com.example.review.domain.Review;
import com.example.review.response.ReviewResponse;
import com.example.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/reviews")
    public List<ReviewResponse> getReviews() {
        return reviewService.getAll();
    }

    @GetMapping("/reviews/average-rating/{productId}")
    public double getAverageRating(@PathVariable Long productId) {
        return reviewService.averageRating(productId);
    }

    @GetMapping("/reviews/{productId}")
    public List<ReviewResponse> getReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @PostMapping("/reviews/{productId}")
    public ReviewResponse addReview(@RequestBody Review review) {
        return reviewService.saveReviews(review);
    }

    @DeleteMapping("/reviews/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReviews(id);
    }

    @PutMapping("/reviews/{id}")
        public ReviewResponse updateReview(@PathVariable Long id, @RequestBody Review review) {
        return reviewService.updateReviews(id, review);
    }
}
