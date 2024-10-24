package com.example.product.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document(collection = "product_images")
public class ProductImage {
    @Id
    private String id;

    private String productId;
    private String imageUrl;
}

