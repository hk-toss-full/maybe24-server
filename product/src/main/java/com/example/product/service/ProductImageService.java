package com.example.product.service;

import com.example.product.entity.ProductImage;
import com.example.product.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    public List<ProductImage> getProductImages(Long productId) {
        return productImageRepository.findByProductId(productId);
    }
}
