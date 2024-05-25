package org.example.projectspring.controller;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Category;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.entity.Review;
import org.example.projectspring.service.BookService;
import org.example.projectspring.service.ReviewService;
import org.example.projectspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookAboutController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/book/{id}")
    public String getBookAboutPage(@PathVariable("id") Integer id, Model model)
    {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);


        model.addAttribute("user", user);
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        List<Category> categories = book.getCategories();

        model.addAttribute("categories", categories);

        List<Review> reviews = reviewService.findByBookId(id);
        model.addAttribute("reviews", reviews);
        return "bookabout";
    }
}
