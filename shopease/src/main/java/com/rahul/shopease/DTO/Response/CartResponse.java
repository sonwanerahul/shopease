package com.rahul.shopease.DTO.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private int cartId;
    private double totalAmmount;
    private int customerId;
    private String customerName;
}
