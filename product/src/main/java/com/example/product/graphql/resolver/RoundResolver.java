package com.example.product.graphql.resolver;

import com.example.product.graphql.dto.RoundOutput;
import com.example.product.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoundResolver {
    private final RoundService roundService;

    @QueryMapping
    public RoundOutput getRoundByProductId(@Argument Long productId) {
        return roundService.getRoundByProductId(productId);
    }
}
