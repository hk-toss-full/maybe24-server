package com.example.product.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roundId;

    private int totalCnt;
    private Date date;
    private int price;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;
}
