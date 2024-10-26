package com.example.product.entity;

import com.example.product.enums.DiscountType;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long discountId;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private int discountRate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}