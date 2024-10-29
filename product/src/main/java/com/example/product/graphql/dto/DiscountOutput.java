package com.example.product.graphql.dto;


import com.example.product.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiscountOutput {
    private Long discountId;
    private DiscountType discountType;
    private int discountRate;
    private Long productId;
}
