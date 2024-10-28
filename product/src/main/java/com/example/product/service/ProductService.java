package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.enums.Category;
import com.example.product.graphql.dto.ProductImgOutput;
import com.example.product.graphql.dto.ProductOutput;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.product.graphql.dto.ProductOutput.convertToProductOutput;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductRepository productRepository;
    private final ProductImgService productImgService;

    @Transactional
    public ProductOutput getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.increaseViewCnt();
        productRepository.save(product);
        return convertToProductOutput(product, productImgService);
    }

    public List<ProductOutput> getAllProducts() {
        List<ProductOutput> list = productRepository.findAll().stream()
                .map(el->convertToProductOutput(el, productImgService))
                .toList();
        return list;
    }

    public List<ProductOutput> findByCategory(Category category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(el->convertToProductOutput(el, productImgService))
                .toList();

    }

    public List<ProductOutput> findByTitle(String title) {
        return productRepository.findByTitleContaining(title)
                .stream()
                .map(el->convertToProductOutput(el, productImgService))
                .toList();
    }
}

