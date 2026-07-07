package com.rahul.shopease.Transformer;

import com.rahul.shopease.DTO.Request.ProductRequest;
import com.rahul.shopease.DTO.Response.ProductResponse;
import com.rahul.shopease.Entity.Product;

public class ProductTransformer {

    public static Product requestToProduct(ProductRequest request){
        return Product.builder()
                .productName(request.getProductName())
                .decsription(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .build();
    }

    public static ProductResponse productToResponse(Product product){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDecsription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
