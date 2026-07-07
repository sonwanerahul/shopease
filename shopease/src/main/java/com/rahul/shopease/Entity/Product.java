package com.rahul.shopease.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="product_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(nullable = false)
    private String productName;

    @Column(length = 1000)
    private String decsription;

    private double price;
    private int stock;
    private String category;
    private String imageUrl;

}
