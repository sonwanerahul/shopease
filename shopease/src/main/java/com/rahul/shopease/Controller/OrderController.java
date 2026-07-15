package com.rahul.shopease.Controller;

import com.rahul.shopease.DTO.Request.OrderRequest;
import com.rahul.shopease.DTO.Request.UpdateOrderStatusRequest;
import com.rahul.shopease.DTO.Response.OrderResponse;
import com.rahul.shopease.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @GetMapping("/get/{oderId}")
    public ResponseEntity<OrderResponse>getOrderById(@PathVariable int oderId){
        OrderResponse orderResponse = orderService.getOderById(oderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> orderResponse= orderService.getAllOrders();
        return ResponseEntity.ok(orderResponse);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(@PathVariable int customerId){
        List<OrderResponse> orderResponses = orderService.getAllOrdersByCustomer(customerId);
        return ResponseEntity.ok(orderResponses);
    }
    @PutMapping("/update-status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@Valid @RequestBody UpdateOrderStatusRequest request){
        OrderResponse orderResponse =orderService.updateOrderStatus(request);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable int orderId){
        OrderResponse orderResponse = orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);

    }
}
