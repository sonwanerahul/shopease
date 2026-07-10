package com.rahul.shopease.Transformer;

import com.rahul.shopease.DTO.Request.CartRequest;
import com.rahul.shopease.DTO.Response.CartResponse;
import com.rahul.shopease.Entity.Cart;


public class CartTransformer {

    public static Cart requestToCart(CartRequest cartRequest) {
        return Cart.builder()
                .totalAmmount(0.0)
                .build();
    }

    public static CartResponse cartToResponse(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getCartId())
                .totalAmmount(cart.getTotalAmmount())
                .customerId(cart.getCustomer().getCustomerId())
                .customerName(cart.getCustomer().getCustomerName())
                .build();
    }

}
