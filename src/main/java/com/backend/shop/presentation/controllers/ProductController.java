package com.backend.shop.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.interfaces.IProductService;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO product){
        productService.createProduct(product);
        return ResponseEntity.ok("Product created");
    }

    
}
