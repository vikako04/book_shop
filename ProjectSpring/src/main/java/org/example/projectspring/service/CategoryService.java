package org.example.projectspring.service;
import org.example.projectspring.entity.Category;
import org.example.projectspring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService
{

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(Category category)
    {
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }
    public Category getCategoryById(int categoryId)
    {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    public List<Category> findByIdIn(List<Integer> categoryIds)
    {
        return categoryRepository.findAllById(categoryIds);
    }
}