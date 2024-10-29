package com.example.product.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document(collection = "productImg")
public class ProductImg {
    @Id
    private String productImgId;
    private String productImgUrl;
    private String productId;
}

