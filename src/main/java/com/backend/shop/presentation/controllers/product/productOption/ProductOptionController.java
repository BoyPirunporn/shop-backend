package com.backend.shop.presentation.controllers.product.productOption;

import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.shop.applications.dto.product.ProductOptionDTO;
import com.backend.shop.applications.interfaces.IProductOptionService;

import java.util.List;


@RestController
@RequestMapping("${application.api.prefix}/product-options")
public class ProductOptionController {
    
    private final IProductOptionService productOptionService;

    public ProductOptionController(IProductOptionService productOptionService){
        this.productOptionService = productOptionService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithPayload<ProductOptionDTO>> getById(@PathVariable Long id){
        ProductOptionDTO productOptionDTO = productOptionService.getById(id);
        return ResponseEntity.ok(new ResponseWithPayload<>(200,productOptionDTO));
    }
    @GetMapping
    public ResponseEntity<ResponseWithPayload<List<ProductOptionDTO>>> getAllProductOption(@RequestParam int page, @RequestParam int size){
        List<ProductOptionDTO> productOptionDTOS = productOptionService.getAllProductOption(page,size);
        ResponseWithPayload<List<ProductOptionDTO>> response = new ResponseWithPayload<>();
        response.setPayload(productOptionDTOS);
        response.setStatus(200);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<ResponseMessage> createProductOption(@RequestBody ProductOptionDTO productOptionDTO ){
        productOptionService.createOption(productOptionDTO);
        return ResponseEntity.ok(new ResponseMessage(200,"Product option has been created."));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateProductOption(@RequestBody ProductOptionDTO productOptionDTO){
        productOptionService.updateOption(productOptionDTO);
        return ResponseEntity.ok(new ResponseMessage(200,"Product option has been updated."));
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<ResponseMessage> deleteProductOption(@RequestParam Long id){
        productOptionService.deleteOption(id);
        return ResponseEntity.ok(new ResponseMessage(200,"Product option has been deleted."));
    }
}
