package org.example.projectspring.controller;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.service.BookService;
import org.example.projectspring.service.CartService;
import org.example.projectspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/addToCart")
    public String addToFavorites(@RequestParam("userId") Integer userId, @RequestParam("bookId") Integer bookId)
    {
        MyUser user = userService.findById(userId);
        Book book = bookService.findById(bookId);
        cartService.addToCart(user, book);

        return "redirect:/korzina";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam("userId") Integer userId, @RequestParam("bookId") Integer bookId) {

        MyUser user = userService.findById(userId);
        Book book = bookService.findById(bookId);
        cartService.removeFromCart(user, book);



        return "redirect:/korzina";
    }
}
