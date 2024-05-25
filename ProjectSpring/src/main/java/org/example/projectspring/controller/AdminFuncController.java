package org.example.projectspring.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.Category;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.service.AuthorService;
import org.example.projectspring.service.CategoryService;
import org.example.projectspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminFuncController
{

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/admin-func")
    public String getAdminFuncPage(Model model, Authentication authentication)
    {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);


        model.addAttribute("user", user);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);

        return "admin-func";
    }


}
