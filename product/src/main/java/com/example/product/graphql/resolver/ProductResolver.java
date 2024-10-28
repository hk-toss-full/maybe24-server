package com.example.product.graphql.resolver;

import com.example.product.entity.ProductImg;
import com.example.product.enums.Category;
import com.example.product.graphql.dto.ProductOutput;
import com.example.product.service.ProductImgService;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductResolver {
    private final ProductService productService;
    private final ProductImgService productImgService;

    @QueryMapping
    public ProductOutput getProductById(@Argument Long productId) {
        ProductOutput productOutput = productService.getProductById(productId);
        ProductImg productImg = productImgService.findByProductId(productId.toString());
        return convertToProductOutputWithImage(productOutput, productImg);
    }

    @QueryMapping
    public List<ProductOutput> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(product -> {
                    ProductImg productImg = productImgService.findByProductId(product.getProductId().toString());
                    return convertToProductOutputWithImage(product, productImg);
                })
                .toList();
    }

    @QueryMapping
    public List<ProductOutput> getProductsByTitle(@Argument String title) {
        return productService.findByTitle(title).stream()
                .map(product -> {
                    ProductImg productImg = productImgService.findByProductId(product.getProductId().toString());
                    return convertToProductOutputWithImage(product, productImg);
                })
                .toList();
    }

    @QueryMapping
    public List<ProductOutput> getProductsByCategory(@Argument Category category) {
        return productService.findByCategory(category).stream()
                .map(product -> {
                    ProductImg productImg = productImgService.findByProductId(product.getProductId().toString());
                    return convertToProductOutputWithImage(product, productImg);
                })
                .toList();
    }

    private ProductOutput convertToProductOutputWithImage(ProductOutput product, ProductImg productImg) {
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
