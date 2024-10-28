package com.example.product.graphql.dto;

import com.example.product.entity.Discount;
import com.example.product.entity.Product;
import com.example.product.entity.ProductImg;
import com.example.product.entity.Round;
import com.example.product.enums.Category;
import com.example.product.service.ProductImgService;
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

    public static ProductOutput convertToProductOutput(Product product, ProductImgService productImgService) {
        ProductImgOutput productImg = convertToProductImgOutput(
                productImgService.findByProductId(product.getProductId().toString())
        );
        return new ProductOutput(
                product.getProductId(),
                product.getTitle(),
                product.getCategory(),
                product.getPlace(),
                product.getDescription(),
                product.getView_cnt(),
                productImg.getProductImgUrl(),
                product.getDateList(),
                product.getDiscounts()
        );
    }

    private static ProductImgOutput convertToProductImgOutput(ProductImg productImg) {
        return new ProductImgOutput(
                productImg.getProductImgId(),
                productImg.getProductImgUrl(),
                productImg.getProductId()
        );
    }

    public static ProductOutput convertToProductOutputWithImage(ProductOutput product, ProductImg productImg) {
        return new ProductOutput(
                product.getProductId(),
                product.getTitle(),
                product.getCategory(),
                product.getPlace(),
                product.getDescription(),
                product.getView_cnt(),
                productImg.getProductImgUrl(),
                product.getDateList(),
                product.getDiscounts()
        );
    }
}
