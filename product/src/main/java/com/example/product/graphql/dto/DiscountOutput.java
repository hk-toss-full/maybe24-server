package com.example.product.graphql.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiscountOutput {
    private Long discountId;
    private String discountType;
    private int discountRate;
    private Long productId;
}
