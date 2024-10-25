package com.example.product.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RoundOutput {
    private Long roundId;
    private Date date;
    private int price;
    private int totalCnt;
    private Long productId;
}
