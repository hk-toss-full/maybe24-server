package com.example.product.service;

import com.example.product.entity.Discount;
import com.example.product.graphql.dto.DiscountOutput;
import com.example.product.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.product.entity.Discount.convertToDiscountOutput;

@RequiredArgsConstructor
@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<DiscountOutput> getDiscountByProductId(Long productId) {
        return discountRepository.findDiscountsByProduct_ProductId(productId)
                .stream()
                .map(Discount::convertToDiscountOutput)
                .toList();
    }

    public DiscountOutput getDiscountById(Long discountId) {
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(() -> new RuntimeException("discount not found"));
        return convertToDiscountOutput(discount);
    }
}
