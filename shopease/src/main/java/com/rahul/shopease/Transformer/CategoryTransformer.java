package com.rahul.shopease.Transformer;

import com.rahul.shopease.DTO.Request.CategoryRequest;
import com.rahul.shopease.DTO.Response.CategoryResponse;
import com.rahul.shopease.Entity.Category;

public class CategoryTransformer {

    public static Category requestToCategory(CategoryRequest request){
        return Category.builder()
                .categoryName(request.getCategoryName())
                .categoryDescription(request.getCategoryDescription())
                .build();
    }
    public static CategoryResponse categoryToCategoryResponse(Category category){
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }
}
