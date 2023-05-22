package com.ecommerce.springbootrestapi.controller;

import com.ecommerce.springbootrestapi.model.Category;
import com.ecommerce.springbootrestapi.service.CategoryService;
import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return ResponseEntity.ok(category);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Category>> getCategoriesByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(categoryService.getCategoriesByProductId(productId));
    }

    @GetMapping("/count")
    public ResponseEntity<List<Object[]>> getCountOfProductsInEachCategory() {
        return ResponseEntity.ok(categoryService.getCountOfProductsInEachCategory());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(category));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
