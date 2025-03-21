package com.backend.shop.presentation.controllers.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.interfaces.IProductService;
import com.backend.shop.domains.ResponseWithPayload;
import com.backend.shop.domains.datatable.DataTableFilter;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("${application.api.prefix}/products")
@Tag(name = "Product Client")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseWithPayload<List<ProductDTO>>> getNewProduct(@RequestParam int page,@RequestParam int size) {
        return ResponseEntity.ok(new ResponseWithPayload<>(200, productService.getAllProduct(page,size)));
    }
}
