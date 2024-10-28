package com.example.product.repository;

import com.example.product.entity.ProductImg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends MongoRepository<ProductImg, String> {
    ProductImg findByProductId(String productId);
}
