package com.example.product.graphql.dto;

import com.example.product.entity.Discount;
import com.example.product.entity.Round;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductOutput {
    private Long productId;
    private String title;
    private String category;
    private String place;
    private String description;
    private int view_cnt;
    private List<Round> dateList;
    private List<Discount> discounts;

}
