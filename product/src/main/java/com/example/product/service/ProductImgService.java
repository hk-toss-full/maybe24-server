package com.example.product.service;

import com.example.product.entity.ProductImg;
import com.example.product.repository.ProductImgRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductImgService {
    private final ProductImgRepository productImgRepository;

    public ProductImg findByProductId(String productId) {
        System.out.println("productID = " + productId);
        return productImgRepository.findByProductId(productId);
    }
}
