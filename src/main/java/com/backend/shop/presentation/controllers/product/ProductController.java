package com.backend.shop.presentation.controllers.product;

import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.interfaces.IProductService;

import java.util.List;

@RestController
@RequestMapping("${application.api.prefix}/products")
public class ProductController {
    
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithPayload<ProductDTO>> getById(@RequestParam Long id){
        return ResponseEntity.ok(new ResponseWithPayload<>(200,null));
    }

    @GetMapping
    public ResponseEntity<ResponseWithPayload<List<ProductDTO>>> getAll(@RequestParam int page, @RequestParam int size){
        List<ProductDTO> productDTOS = productService.getAllProduct(page,size);
        return ResponseEntity.ok(new ResponseWithPayload<>(200,productDTOS));
    }
    @PostMapping
    public ResponseEntity<ResponseMessage> createProduct(@RequestBody ProductDTO product){
        productService.createProduct(product);
        return ResponseEntity.ok(new ResponseMessage(200,"Product has been created."));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateProduct(@RequestBody ProductDTO product){
        productService.updateProduct(product);
        return ResponseEntity.ok(new ResponseMessage(200,"Product has been updated."));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ResponseMessage(200,"Product has been deleted."));
    }

    
}
