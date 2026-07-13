package com.rahul.shopease.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;
    private int quantity;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
