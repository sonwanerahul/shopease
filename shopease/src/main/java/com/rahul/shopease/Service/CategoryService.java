package com.rahul.shopease.Service;

import com.rahul.shopease.DTO.Request.CategoryRequest;
import com.rahul.shopease.DTO.Response.CategoryResponse;
import com.rahul.shopease.Entity.Category;
import com.rahul.shopease.Exception.CategoryInUseException;
import com.rahul.shopease.Exception.CategoryNotFoundException;
import com.rahul.shopease.Repository.CategoryRepository;
import com.rahul.shopease.Transformer.CategoryTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    //Add Category
    public CategoryResponse addCategory(CategoryRequest request){
        Category category= CategoryTransformer.requestToCategory(request);
        Category savedCategory = categoryRepository.save(category);
        return CategoryTransformer.categoryToCategoryResponse(savedCategory);
    }

    //Get All Categories
    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryTransformer::categoryToCategoryResponse)
                .toList();
    }

    //Get Category By ID
    public CategoryResponse getCategoryById(int categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));
        return CategoryTransformer.categoryToCategoryResponse(category);


    }

    //Update Category
    public CategoryResponse updateCategoryById(int categoryId,CategoryRequest request ){
        Category category =categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));
        category.setCategoryName(request.getCategoryName());
        category.setCategoryDescription(request.getCategoryDescription());

        Category updateCategory = categoryRepository.save(category);
        return CategoryTransformer.categoryToCategoryResponse(updateCategory);
    }

    //Delete Category
    public String deleteCategoryById(int categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));
        try {
            categoryRepository.delete(category);
        }
        catch(Exception e){
            throw new CategoryInUseException("Category cannot be deleted because products are assigner to it");
            }
        return "Category deleted successfully";
    }
}
