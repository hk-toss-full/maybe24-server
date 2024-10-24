package com.example.product.graphql.resolver;

import com.example.product.graphql.dto.DiscountOutput;
import com.example.product.service.DiscountService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DiscountResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    DiscountService discountService;
    public DiscountOutput getDiscountById(Long id){
        return discountService.getDiscountById(id);
    }

    public List<DiscountOutput> getAllDiscounts(){
        return discountService.getAllDiscounts();
    }
}
