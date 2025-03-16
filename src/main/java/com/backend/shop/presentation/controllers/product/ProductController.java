package com.backend.shop.presentation.controllers.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("${application.api.prefix}/products")
@Tag(name = "Product Client")
public class ProductController {
    
}
