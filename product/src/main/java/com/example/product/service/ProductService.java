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

    public ProductOutput getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToProductOutput(product);
    }

    public List<ProductOutput> getAllProducts() {
        List<ProductOutput> list = productRepository.findAll().stream()
                .map(this::convertToProductOutput)
                .toList();
        System.out.println("productList: "+list);
        return list;
    }

    public List<ProductOutput> findByCategory(String category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(this::convertToProductOutput)
                .toList();

    }

    public List<ProductOutput> findByTitle(String title) {
        return productRepository.findByTitleContaining(title)
                .stream()
                .map(this::convertToProductOutput)
                .toList();
    }

    private ProductOutput convertToProductOutput(Product product) {
        return new ProductOutput(
                product.getProductId(),
                product.getTitle(),
                product.getCategory(),
                product.getPlace(),
                product.getDescription(),
                product.getView_cnt(),
                product.getDateList(),
                product.getDiscounts()
        );
    }
}

