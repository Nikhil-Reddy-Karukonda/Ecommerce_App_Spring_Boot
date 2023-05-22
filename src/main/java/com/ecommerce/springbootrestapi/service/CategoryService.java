package com.ecommerce.springbootrestapi.service;

import com.ecommerce.springbootrestapi.model.Category;
import com.ecommerce.springbootrestapi.repositoryDAO.CategoryRepository;
import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Category> getCategoriesByProductId(Long productId) {
        return categoryRepository.findByProducts_Id(productId);
    }

    @Transactional(readOnly = true)
    public List<Object[]> getCountOfProductsInEachCategory() {
        return categoryRepository.countProductsInEachCategory();
    }

    @Transactional
    public Category saveCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        // if (!categoryRepository.findById(id).isPresent()) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Category with id: " + id + " not found");
        }
    }
}
