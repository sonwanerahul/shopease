package com.rahul.shopease.Controller;

import com.rahul.shopease.DTO.Request.CategoryRequest;
import com.rahul.shopease.DTO.Response.CategoryResponse;
import com.rahul.shopease.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public CategoryResponse addCategory (@Valid @RequestBody CategoryRequest category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/all")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getCategoryById(@Valid @PathVariable("categoryId") int categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse updateCategory(@PathVariable("categoryId") int categoryId,
                                           @Valid @RequestBody CategoryRequest request) {
        return categoryService.updateCategoryById(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") int categoryId) {
        return  categoryService.deleteCategoryById(categoryId);
    }

}
