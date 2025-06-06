package com.example.product.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductImgOutput {
    private String productImgId;
    private String productImgUrl;
    private String productId;
}
