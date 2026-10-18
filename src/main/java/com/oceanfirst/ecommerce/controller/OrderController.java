package com.oceanfirst.ecommerce.controller;

import com.oceanfirst.ecommerce.dto.request.CreateOrderRequest;
import com.oceanfirst.ecommerce.dto.response.ApiResponse;
import com.oceanfirst.ecommerce.dto.response.OrderResponse;
import com.oceanfirst.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            Authentication authentication,
            @Valid @RequestBody CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(authentication.getName(), request);
        return ResponseEntity.ok(ApiResponse.success("Order created", response));
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getMyOrders(
            Authentication authentication) {
        List<OrderResponse> orders = orderService.getMyOrders(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved", orders));
    }
}