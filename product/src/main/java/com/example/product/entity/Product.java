package com.example.product.entity;

import com.example.product.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Product {
    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private String place;
    private int view_cnt;

    @OneToMany(mappedBy = "product")
    private List<Round> dateList;

    @OneToMany(mappedBy = "product")
    private List<Discount> discounts;

    public void increaseViewCnt() {
        this.view_cnt++;
    }
}
