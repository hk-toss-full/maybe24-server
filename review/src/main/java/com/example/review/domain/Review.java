package com.example.review.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

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
    @CreatedDate() @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") @Builder.Default
    private LocalDateTime createdAt=LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private Long productId;
}
