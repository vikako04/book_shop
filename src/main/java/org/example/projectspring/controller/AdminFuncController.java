package org.example.projectspring.controller;

import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.Category;
import org.example.projectspring.service.AuthorService;
import org.example.projectspring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminFuncController
{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/admin-func.html")
    public String getAdminFuncPage(Model model)
    {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);

        return "admin-func.html";
    }


}
