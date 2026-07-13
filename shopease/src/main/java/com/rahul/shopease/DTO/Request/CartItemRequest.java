package com.rahul.shopease.DTO.Request;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequest {
    @Positive(message="cart ID must be greather than 0")
    private int cartId;
    @Positive(message="Product ID must be greather than 0")
    private int productId;
    @Positive(message="Quantity must be greather than 0")
    private int quantity;
}
