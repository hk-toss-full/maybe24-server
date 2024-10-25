package com.example.product.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Product {
    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String category;
    private String title;
    private String description;
    private String place;
    private int view_cnt;

    @OneToMany(mappedBy = "product")
    private List<Round> dateList;

    @OneToMany(mappedBy = "product")
    private List<Discount> discounts;
}
