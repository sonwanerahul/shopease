package com.rahul.shopease.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private double totalAmmount;

    @OneToOne
    @JoinColumn(name="customer_Id")
    private Customer customer;

    @OneToMany(mappedBy ="cart")
    private List<CartItems> cartItems;
}
