package org.example.projectspring.controller;

import org.example.projectspring.entity.Category;
import org.example.projectspring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("categoryName") String categoryName)
    {

        Category category = new Category();
        category.setCategoryName(categoryName);
        categoryService.addCategory(category);

        return "redirect:/admin-func";
    }
}
