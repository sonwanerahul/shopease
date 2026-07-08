package com.rahul.shopease.Controller;

import com.rahul.shopease.DTO.Request.ProductRequest;
import com.rahul.shopease.DTO.Response.ProductResponse;
import com.rahul.shopease.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponse addProduct(@Valid @RequestBody ProductRequest request){
        return productService.addProduct(request);
    }

    @GetMapping("/all")
    public List<ProductResponse> allProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable("productId") int productId){
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProductById(@PathVariable("productId") int productId,@Valid @RequestBody ProductRequest request){
        return productService.updateProduct(productId,request);
    }
    @DeleteMapping("/{productId}")
    public String deleteProductById(@PathVariable("productId") int productId){
        return productService.deleteProduct(productId);
    }
}
