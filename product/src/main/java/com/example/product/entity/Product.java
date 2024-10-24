package com.example.product.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    private String category;
    private String title;
    private String description;
    private int view_cnt;
    private int price;

    @OneToMany(mappedBy = "product")
    private List<Round> dateList;

    @OneToMany(mappedBy = "product")
    private List<Discount> discounts;
}
