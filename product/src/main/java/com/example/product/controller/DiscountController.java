package com.example.product.controller;

import com.example.product.graphql.dto.DiscountOutput;
import com.example.product.service.DiscountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<DiscountOutput>> getAllDiscounts(){
        List<DiscountOutput> discounts = discountService.getAllDiscounts();
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountOutput> getDiscountById(@PathVariable("id") Long id){
        DiscountOutput discount = discountService.getDiscountById(id);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }
}
