package org.example.projectspring.controller;

import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Category;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.service.AuthorService;
import org.example.projectspring.service.BookService;
import org.example.projectspring.service.CategoryService;
import org.example.projectspring.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

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
            String imagePath = "/assets/images/"+fileName;
            try
            {
                photo.transferTo(new File(filePath));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            author.setPhoto(imagePath);
        }


        authorService.addAuthor(author);

        return "redirect:/admin-func";
    }

    @GetMapping("/author/{id}")
    public String getAuthorDetails(@PathVariable("id") Integer id, Model model) {

        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);


        model.addAttribute("user", user);
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);

        List<Category> categories = author.getCategories();
        model.addAttribute("categories", categories);

        List<Book> books = bookService.getBooksByAuthor(id);
        model.addAttribute("books", books);
        return "author";
    }
}
