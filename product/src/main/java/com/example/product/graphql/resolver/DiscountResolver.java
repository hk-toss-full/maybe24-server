package com.example.product.graphql.resolver;

import com.example.product.graphql.dto.DiscountOutput;
import com.example.product.service.DiscountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DiscountResolver {
    private final DiscountService discountService;

    @QueryMapping
    public DiscountOutput getDiscountById(@Argument Long discountId){
        return discountService.getDiscountById(discountId);
    }

    @QueryMapping
    public List<DiscountOutput> getDiscountsByProductId(@Argument Long productId){
        return discountService.getDiscountByProductId(productId);
    }
}
