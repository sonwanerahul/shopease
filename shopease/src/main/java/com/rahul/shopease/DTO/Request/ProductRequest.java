package com.rahul.shopease.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message="Product name is required")
    private String productName;
    @NotBlank(message="Description is required")
    private String description;
    @Positive(message ="Price must be greather than 0")
    private double price;
    @PositiveOrZero(message="Stock can not be Negative")
    private int stock;
    @NotBlank(message="Category  is required")
    private String category;
    @NotBlank(message="Image Url  is required")
    private String imageUrl;
}
