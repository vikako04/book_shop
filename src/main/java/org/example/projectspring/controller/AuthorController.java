package org.example.projectspring.controller;

import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.Category;
import org.example.projectspring.service.AuthorService;
import org.example.projectspring.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;



    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestParam("fullName") String fullName,
                            @RequestParam("yearsOfLife") String yearsOfLife,
                            @RequestParam("yearsOfCreativity") String yearsOfCreativity,
                            @RequestParam("biography") String biography,
                            @RequestParam("photo") MultipartFile photo,
                            @RequestParam("categories") List<Integer> categoryIds)
    {



        Author author = new Author();
        author.setFullName(fullName);
        author.setYearsOfLife(yearsOfLife);
        author.setYearsOfCreativity(yearsOfCreativity);
        author.setBiography(biography);

        List<Category> categories = categoryService.findByIdIn(categoryIds);
        author.setCategories(categories);

        if (photo!=null)
        {
            String fileName = photo.getOriginalFilename();
            String filePath = uploadPath + "/" + fileName;
            try
            {
                photo.transferTo(new File(filePath));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            author.setPhoto(filePath);
        }


        authorService.addAuthor(author);

        return "redirect:/admin-func.html";
    }
}
