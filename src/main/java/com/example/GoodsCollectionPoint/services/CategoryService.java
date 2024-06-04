package com.example.GoodsCollectionPoint.services;

import com.example.GoodsCollectionPoint.models.Category;
import com.example.GoodsCollectionPoint.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found."));
    }

    public Category saveCategory(Category category) {
        log.info("Creating new category. Name: {};", category.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        log.info("Deleting category. Id: {};", id);
        categoryRepository.deleteById(id);
    }
}
