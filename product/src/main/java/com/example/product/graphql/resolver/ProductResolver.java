package com.example.product.graphql.resolver;

import com.example.product.graphql.dto.ProductOutput;
import com.example.product.service.ProductService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private final ProductService productService;

    public ProductOutput getProductById(Long id) {
        return productService.findById(id);
    }

    public List<ProductOutput> getAllProducts() {
        return productService.findAll();
    }
}
