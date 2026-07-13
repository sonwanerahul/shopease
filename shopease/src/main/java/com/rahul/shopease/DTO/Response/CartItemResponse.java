package com.rahul.shopease.DTO.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {
    private int cartId;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;

    //in future fields image url and stocks
}
