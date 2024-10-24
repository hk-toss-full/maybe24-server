package com.example.product.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductOutput {
    private Long id;
    private String title;
    private String category;
    private String description;
    private int price;
}
