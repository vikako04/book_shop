package org.example.projectspring.controller;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.service.BookService;
import org.example.projectspring.service.FavoriteService;
import org.example.projectspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/addToFav")
    public String addToFavorites(@RequestParam("userId") Integer userId, @RequestParam("bookId") Integer bookId)
    {
         MyUser user = userService.findById(userId);
         Book book = bookService.findById(bookId);
         favoriteService.addToFavorites(user, book);

        return "redirect:/favorites";
    }

    @PostMapping("/removeFromFav")
    public String removeFromFavorites(@RequestParam("userId") Integer userId, @RequestParam("bookId") Integer bookId) {

        MyUser user = userService.findById(userId);
        Book book = bookService.findById(bookId);
        favoriteService.removeFromFavorites(user, book);


        return "redirect:/favorites";
    }
}