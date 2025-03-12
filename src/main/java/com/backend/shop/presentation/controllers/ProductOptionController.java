package com.backend.shop.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.product.ProductOptionDTO;


@RestController
@RequestMapping("${api.prefix}/product-options")
public class ProductOptionController {
    
    @PostMapping
    public ResponseEntity<String> createProductOption(@RequestBody ProductOptionDTO productOptionDTO ){
        return ResponseEntity.ok("Product option created");
    }
}
