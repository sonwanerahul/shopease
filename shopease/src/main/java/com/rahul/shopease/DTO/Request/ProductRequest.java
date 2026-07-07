package com.rahul.shopease.DTO.Request;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String productName;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String imageUrl;
}
