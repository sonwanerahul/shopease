package com.rahul.shopease.Controller;

import com.rahul.shopease.DTO.Request.CartRequest;
import com.rahul.shopease.DTO.Response.CartResponse;
import com.rahul.shopease.Service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public CartResponse addCart(@Valid @RequestBody CartRequest cartRequest) {
        return  cartService.addCart(cartRequest);
    }

    @GetMapping("/{cartId}")
    public CartResponse getCartById(@PathVariable("cartId") int cartId){
        return  cartService.getCartById(cartId);
    }

    @GetMapping("/customer/{customerId}")
    public CartResponse getCartByCustomerId(@PathVariable("customerId") int customerId){
        return  cartService.getCartByCustomerId(customerId);
    }
    @GetMapping("/all")
    public List<CartResponse> getAllCarts(){
        return cartService.getAllCarts();
    }

    @DeleteMapping("/delete/{cartId}")
    public String deleteCart(@PathVariable("cartId") int cartId){
        return  cartService.deleteCart(cartId);
    }

}
