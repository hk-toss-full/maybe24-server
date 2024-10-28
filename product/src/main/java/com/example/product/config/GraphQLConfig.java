package com.example.product.config;

import com.example.product.enums.Category;
import com.example.product.service.DiscountService;
import com.example.product.service.ProductService;
import com.example.product.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;

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
                            Long id = parseLongArgument(env.getArgument("productId"));
                            return productService.getProductById(id);
                        })
                        .dataFetcher("getAllProducts", env -> productService.getAllProducts())
                        .dataFetcher("getProductsByTitle", env -> {
                            String title = env.getArgument("title");
                            return productService.findByTitle(title);
                        })
                        .dataFetcher("getProductsByCategory", env -> {
                            String categoryString = env.getArgument("category");
                            Category category = Category.valueOf(categoryString.toUpperCase());
                            return productService.findByCategory(category);
                        })
                        .dataFetcher("getDiscountById", env -> {
                            Long id = parseLongArgument(env.getArgument("discountId"));
                            return discountService.getDiscountById(id);
                        })
                        .dataFetcher("getDiscountsByProductId", env -> {
                            Long productId = parseLongArgument(env.getArgument("productId"));
                            return discountService.getDiscountByProductId(productId);
                        })
                        .dataFetcher("getRoundByProductId", env -> {
                            Long productId = parseLongArgument(env.getArgument("productId"));
                            return roundService.getRoundByProductId(productId);
                        })
                )
                .type("ProductOutput", builder -> builder)
                .type("DiscountOutput", builder -> builder)
                .type("RoundOutput", builder -> builder);
    }



        private Long parseLongArgument(Object argument) {
        if (argument instanceof Long) {
            return (Long) argument;
        } else if (argument instanceof Integer) {
            return ((Integer) argument).longValue();
        } else if (argument instanceof String) {
            return Long.parseLong((String) argument);
        } else {
            throw new IllegalArgumentException("Invalid argument type: " + argument.getClass().getName());
        }
    }
}

