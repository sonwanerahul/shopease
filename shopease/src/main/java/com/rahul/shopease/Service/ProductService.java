package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.ProductRequest;
import com.rahul.shopease.DTO.Response.ProductResponse;
import com.rahul.shopease.Entity.Category;
import com.rahul.shopease.Entity.Product;
import com.rahul.shopease.Exception.CategoryNotFoundException;
import com.rahul.shopease.Exception.ProductNotFoundException;
import com.rahul.shopease.Repository.CategoryRepository;
import com.rahul.shopease.Repository.ProductRepository;
import com.rahul.shopease.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    // Add Product
    public ProductResponse addProduct(ProductRequest request){
        Product product = ProductTransformer.requestToProduct(request);
        Category category=categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException("Category Not Found"));
        product.setCategory(category);
        Product savedProduct=productRepository.save(product);
        return ProductTransformer.productToResponse(savedProduct);
    }

    //Get All product
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductTransformer :: productToResponse)
                .toList();
    }

        //get Product By ID
    public ProductResponse getProductById(int productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(( ) -> new ProductNotFoundException("Product not found"));
        return ProductTransformer.productToResponse(product);
    }

    //Update Product
    public ProductResponse updateProduct(int productId,ProductRequest request){
        Product product = productRepository.findById(productId)
                .orElseThrow(( ) -> new ProductNotFoundException("Product not found"));

        product.setProductName(request.getProductName());
        product.setDecsription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Category category = categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(()->new CategoryNotFoundException("Category not found"));
        product.setCategory(category);
        product.setImageUrl(request.getImageUrl());

        Product updatedProduct=productRepository.save(product);
        return ProductTransformer.productToResponse(updatedProduct);
    }

        //Delete Product
    public String deleteProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(( ) -> new ProductNotFoundException("Product not found"));
        productRepository.delete(product);
        return "Product delete Sucessfully";
    }
}
