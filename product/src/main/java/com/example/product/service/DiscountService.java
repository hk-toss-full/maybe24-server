package com.example.product.service;

import com.example.product.entity.Discount;
import com.example.product.graphql.dto.DiscountOutput;
import com.example.product.repository.DiscountRepository;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public List<DiscountOutput> getAllDiscounts() {
        return discountRepository.findAll()
                .stream()
                .map(this::convertToDiscountOutput)
                .toList();
    }

    public DiscountOutput getDiscountById(Long id) {
        Discount discount = discountRepository.findById(id)
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
