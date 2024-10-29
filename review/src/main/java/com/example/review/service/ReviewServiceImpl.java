package com.example.review.service;

import com.example.review.repository.ReviewRepository;
import com.example.review.response.ReviewResponse;
import com.example.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    public final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewResponse> list = reviews.stream().map(ReviewResponse::from).toList();
        return list;
    }

    @Transactional
    @Override
    public ReviewResponse saveReviews(Review review) {
        Review save = reviewRepository.save(review);
        return ReviewResponse.from(save);
    }

    @Override
    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        List<Review> reviewList = reviewRepository.findByProductId(productId);
        List<ReviewResponse> list = reviewList.stream().map(ReviewResponse::from).toList();
        return list;
    }

    @Override
    public void deleteReviews(long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewResponse updateReviews(long id, Review review) {
        Optional<Review> byId = reviewRepository.findById(id);
        if (byId.isPresent()) {
            Review existingReview = byId.get();
            Review updateReview = Review.builder()
                    .reviewId(existingReview.getReviewId())
                    .content(review.getContent() == null ? existingReview.getContent() : review.getContent())
                    .author(review.getAuthor() == null ? existingReview.getAuthor() : review.getAuthor())
                    .rating(review.getRating())
                    .productId(existingReview.getProductId())
                    .build();

            Review save = reviewRepository.save(updateReview);
            return ReviewResponse.from(save);

        }else{
            throw new RuntimeException("해당 리뷰를 찾을 수 없습니다.");
        }
    }

    @Override
    public double averageRating(long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        if (reviews.isEmpty()) {
            return 0;
        }else {
            double totalRating = reviews.stream().mapToInt(Review::getRating).sum();
            return totalRating/reviews.size();
        }

    }
}