package com.rahul.shopease.DTO.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private int productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String imageUrl;
}
