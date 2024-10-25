package com.example.product.service;

import com.example.product.entity.Discount;
import com.example.product.graphql.dto.DiscountOutput;
import com.example.product.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<DiscountOutput> getDiscountByProductId(Long productId) {
        return discountRepository.findDiscountsByProduct_ProductId(productId)
                .stream()
                .map(this::convertToDiscountOutput)
                .toList();
    }

    public DiscountOutput getDiscountById(Long discountId) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new RuntimeException("discount not found"));
        return convertToDiscountOutput(discount);
    }

    private DiscountOutput convertToDiscountOutput(Discount discount) {
        return new DiscountOutput(
                discount.getDiscountId(),
                discount.getDiscountType(),
                discount.getDiscountRate(),
                discount.getProduct().getProductId()
        );
    }
}
