package com.example.product.graphql.dto;

import com.example.product.entity.Discount;
import com.example.product.entity.Round;
import com.example.product.enums.Category;
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
    private Category category;
    private String place;
    private String description;
    private int view_cnt;
    private String productImgUrl;
    private List<Round> dateList;
    private List<Discount> discounts;

}