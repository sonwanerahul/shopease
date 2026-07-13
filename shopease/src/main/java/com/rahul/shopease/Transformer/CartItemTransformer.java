package com.rahul.shopease.Transformer;

import com.rahul.shopease.DTO.Request.CartItemRequest;
import com.rahul.shopease.DTO.Request.CartRequest;
import com.rahul.shopease.DTO.Response.CartItemResponse;
import com.rahul.shopease.Entity.CartItems;

public class CartItemTransformer {

    public static CartItems requestToCartItems(CartItemRequest cartItemRequest) {
        return CartItems.builder()
                .quantity(cartItemRequest.getQuantity())
                .build();
    }

    public static CartItemResponse cartItemToResponse(CartItems cartItems) {
        return CartItemResponse.builder()
                .cartId(cartItems.getCartItemId())
                .productName(cartItems.getProduct().getProductName())
                .quantity(cartItems.getQuantity())
                .price(cartItems.getProduct().getPrice())
                .totalPrice(cartItems.getTotalPrice())
                .build();
    }
}
