package org.example.projectspring.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Category;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
public class MainController
{
    @Autowired
    private BookService bookService;


    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String getMainPage(@RequestParam(name = "price-from", required = false) Integer priceFrom,
                              @RequestParam(name = "price-to", required = false) Integer priceTo,
                              @RequestParam(name = "authors", required = false) List<Integer> authorIds,
                              @RequestParam(name = "categories", required = false) List<Integer> categoryIds,
                              @RequestParam(name = "rating", required = false) Integer rating,
                              Model model)
    {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        MyUser user = userService.findMyUserByUsername(userName);
        model.addAttribute("user", user);

        List<Book> books;
        if (priceFrom != null || priceTo != null || authorIds != null || categoryIds != null || rating != null) {
            books = bookService.filterBooks(priceFrom, priceTo, authorIds, categoryIds, rating);
        } else {
            books = bookService.getAllBooks();
        }
        model.addAttribute("books", books);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);

        int minPrice = bookService.findMinPrice();
        int maxPrice = bookService.findMaxPrice();
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "main";
    }
    


    @GetMapping("/register")
    public String getRegPage()
    {

        return "reg";
    }


    @GetMapping("/admin")
    public String adminProfile(Model model, Authentication authentication) {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);


        model.addAttribute("user", user);

        return "admin";
    }





    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private CartService cartService;

    @GetMapping("/favorites")
    public String getFavoritesPage(Model model, Authentication authentication)
    {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);


        List<Book> favoriteBooks = favoriteService.findBooksByUserId(user.getId());

        model.addAttribute("books", favoriteBooks);
        model.addAttribute("user", user);
        return "favorites";
    }
    @GetMapping("/korzina")
    public String getKorzinaPage(Model model, Authentication authentication)
    {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);


        List<Book> booksInCart = cartService.findBooksByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("books", booksInCart);
        return "korzina";
    }
    @GetMapping("/login")
    public String getLogRegPage(Model model, @RequestParam(value = "error", required = false) String error)
    {
        if (error != null) {
            model.addAttribute("errorMessage", "Неверные имя пользователи или пароль");
        }
        return "log";
    }




    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);



        model.addAttribute("user", user);

        return "profile";
    }

}
