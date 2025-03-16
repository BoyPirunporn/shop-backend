package com.backend.shop.presentation.controllers.admin.order;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.order.OrderDTO;
import com.backend.shop.applications.dto.order.request.OrderRequest;
import com.backend.shop.applications.interfaces.IOrderService;
import com.backend.shop.domains.ResponseMessage;
import com.backend.shop.domains.ResponseWithPayload;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Order Admin Controller")
@RequestMapping("${application.api.prefix}/admin/orders")
public class OrderAdminController {
    
    private final IOrderService orderService;

    public OrderAdminController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{id}") ResponseEntity<ResponseWithPayload<OrderDTO>>getById(@PathVariable String name){
        return ResponseEntity.ok(new ResponseWithPayload<>(200,null));
    }
    @GetMapping 
    ResponseEntity<ResponseWithPayload<List<OrderDTO>>>getAll(@RequestParam int page,@RequestParam int size){
        return ResponseEntity.ok(new ResponseWithPayload<>(200,orderService.getAllOrder(page, size)));
    }
    @PostMapping 
    ResponseEntity<ResponseMessage>createOrder(@RequestBody OrderRequest order){
        orderService.createOrder(order);
        return ResponseEntity.ok(new ResponseMessage(200,"Order has been created successfully"));
    }
    @PutMapping 
    ResponseEntity<ResponseMessage>updateOrder(@RequestBody OrderDTO order){
        return ResponseEntity.ok(new ResponseMessage(200,"Order has been updated successfully"));
    }
    @DeleteMapping("{id}")
    ResponseEntity<ResponseMessage>deleteOrderById(@PathVariable Long id){
        return ResponseEntity.ok(new ResponseMessage(200,"Order has been deleted successfully"));
    }
}
