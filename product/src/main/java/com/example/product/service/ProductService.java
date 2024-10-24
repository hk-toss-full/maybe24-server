package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.graphql.dto.ProductOutput;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductRepository productRepository;

    public ProductOutput findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToProductOutput(product);
    }

    public List<ProductOutput> findAll() {
        return productRepository.findAll().stream()
                .map(this::convertToProductOutput)
                .toList();
    }

    private ProductOutput convertToProductOutput(Product product) {
        return new ProductOutput(
                product.getProductId(),
                product.getTitle(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice()
        );
    }
}

