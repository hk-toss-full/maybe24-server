package com.example.product.repository;

import com.example.product.entity.Product;
import com.example.product.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    List<Product> findByTitleContaining(String title);
    List<Product> findTop3ByCategoryOrderByViewCntDesc(Category category);
    List<Product> findTop7ByOrderByViewCntDesc();
}
