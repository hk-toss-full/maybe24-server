package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.entity.ProductImg;
import com.example.product.graphql.dto.ProductImgOutput;
import com.example.product.graphql.dto.ProductOutput;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return convertToProductOutput(product);
    }

    public List<ProductOutput> getAllProducts() {
        List<ProductOutput> list = productRepository.findAll().stream()
                .map(this::convertToProductOutput)
                .toList();
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
        ProductImgOutput productImg = convertToProductImgOutput(
                productImgService.findByProductId(product.getProductId().toString())
        );
        return new ProductOutput(
                product.getProductId(),
                product.getTitle(),
                product.getCategory(),
                product.getPlace(),
                product.getDescription(),
                product.getView_cnt(),
                productImg.getProductImgUrl(),
                product.getDateList(),
                product.getDiscounts()
        );
    }

//    @Transactional
//    public void increaseViewCnt(Long productId) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//        product.increaseViewCnt();
//        productRepository.save(product);
//    }

    private ProductImgOutput convertToProductImgOutput(ProductImg productImg) {
        return new ProductImgOutput(
                productImg.getProductImgId(),
                productImg.getProductImgUrl(),
                productImg.getProductId()
        );
    }
}

