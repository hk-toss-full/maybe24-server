package com.example.product.config;

import com.example.product.service.DiscountService;
import com.example.product.service.ProductService;
import com.example.product.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;

import graphql.schema.idl.RuntimeWiring;

@RequiredArgsConstructor
@Component
public class GraphQLConfig {
    private final ProductService productService;
    private final DiscountService discountService;
    private final RoundService roundService;

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.type("Query", builder -> builder
                .dataFetcher("getProductById", env -> {
                    Long id = env.getArgument("id");
                    return productService.getProductById(id);
                })
                .dataFetcher("getAllProducts", env -> productService.getAllProducts())
                .dataFetcher("getProductsByTitle", env -> {
                    String title = env.getArgument("title");
                    return productService.findByTitle(title);
                })
                .dataFetcher("getProductsByCategory", env -> {
                    String category = env.getArgument("category");
                    return productService.findByCategory(category);
                })
                .dataFetcher("getDiscountById", env -> {
                    Long id = env.getArgument("id");
                    return discountService.getDiscountById(id);
                })
                .dataFetcher("getDiscountsByProductId", env -> {
                    Long productId = env.getArgument("productId");
                    return discountService.getDiscountByProductId(productId);
                })
                .dataFetcher("getRoundByProductId", env -> {
                    Long productId = env.getArgument("productId");
                    return roundService.getRoundByProductId(productId);
                }))
                .type("ProductOutput", builder -> builder)
                .type("DiscountOutput", builder -> builder)
                .type("RoundOutput", builder -> builder);
    }
}
