package com.rahul.shopease.Controller;

import com.rahul.shopease.DTO.Request.CartItemRequest;
import com.rahul.shopease.DTO.Response.CartItemResponse;
import com.rahul.shopease.Service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping("/add")
    public CartItemResponse addCartItem(@Valid @RequestBody CartItemRequest cartItemRequest){
        return cartItemService.addCartItem(cartItemRequest);
    }

    @GetMapping("/{cartItemId}")
    public CartItemResponse getCartItemById(@PathVariable("cartItemId") int cartItemId){
        return cartItemService.getCartItemById(cartItemId);
    }
    @GetMapping("/all")
    public List<CartItemResponse> getAllCartItems(){
        return cartItemService.getAllCartItems();
    }
    @DeleteMapping("/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") int cartItemId){
       return cartItemService.deleteCartItem(cartItemId);
    }

    @PutMapping("/update-quantity/{cartItemId}/{quantity}")
    public CartItemResponse updateQuantity(@PathVariable("cartItemId") int cartItemId,
                                           @PathVariable("quantity") int quantity){
        return cartItemService.updateCartItemQuantity(cartItemId,quantity);
    }
}
