package com.example.review.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "REVIEW"
)
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column(nullable = false)
    private String author;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Rating rating;
//    @ManyToOne
//    private Product productId;
}
