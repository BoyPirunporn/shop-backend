package com.backend.shop.presentation.controllers.admin.product;

import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;

import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.datatable.ResponseDataTable;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.dto.product.request.ProductRequestDTO;
import com.backend.shop.applications.interfaces.IProductService;

import java.io.IOException;
import java.util.List;

@RestController
@Tag(name = "Product Admin Controller")
@RequestMapping("${application.api.prefix}/admin/products")
public class ProductAdminController {

    private final IProductService productService;

    public ProductAdminController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithPayload<ProductDTO>> getById(@PathVariable String id) {
       ProductDTO productDTO = productService.getProductById(Long.parseLong(id));
        return ResponseEntity.ok(new ResponseWithPayload<>(200, productDTO));
    }

    @GetMapping
    public ResponseEntity<ResponseDataTable<ProductDTO>> getAll(@ModelAttribute DataTableFilter filter) {
        return ResponseEntity.ok(productService.filterProduct(filter));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseMessage> createProduct(@ModelAttribute ProductRequestDTO product) throws IOException {
        productService.createProduct(product);
        return ResponseEntity.ok(new ResponseMessage(200, "Product has been created."));
    }

    @PutMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseMessage> updateProduct(@ModelAttribute ProductRequestDTO product) throws IOException {
        productService.updateProduct(product);
        return ResponseEntity.ok(new ResponseMessage(200, "Product has been updated."));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ResponseMessage(200, "Product has been deleted."));
    }

}
