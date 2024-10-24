package com.example.product.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long discountId;

    private String discountType;
    private int discountRate;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
}