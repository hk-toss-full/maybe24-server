package com.example.product.graphql.resolver;

import com.example.product.graphql.dto.ProductOutput;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductResolver{
    private final ProductService productService;

    @QueryMapping
    public ProductOutput getProductById(@Argument Long id) {
        return productService.getProductById(id);
    }

    @QueryMapping
    public List<ProductOutput> getAllProducts() {
        System.out.println("getAllProducts called");
        return productService.getAllProducts();
    }
    @QueryMapping
    public List<ProductOutput> getProductsByTitle(@Argument String title) { return productService.findByTitle(title); }
    @QueryMapping
    public List<ProductOutput> getProductsByCategory(@Argument String category) { return productService.findByCategory(category); }
}
