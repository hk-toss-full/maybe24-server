package com.example.product.controller;

import com.example.product.graphql.dto.ProductOutput;
import com.example.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductOutput>> getAllProducts() {
        List<ProductOutput> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductOutput> getProductById(@PathVariable Long id) {
        ProductOutput product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
