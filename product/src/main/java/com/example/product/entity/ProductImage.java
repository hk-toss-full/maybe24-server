package com.example.product.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document(collection = "productImg")
public class ProductImage {
    @Id
    private String id;
    private Long productId;
    private String imageUrl;
}

