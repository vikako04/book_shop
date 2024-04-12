package org.example.projectspring.controller;

import org.example.projectspring.entity.Book;
import org.example.projectspring.service.BookService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Controller
public class MainController
{
    @Autowired
    private BookService bookService;

    @GetMapping("/main.html")
    public String getMainPage(Model model)
    {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);

        return "main";
    }






    @GetMapping("/bookabout.html")
    public String getBookAboutPage()
    {
        return "bookabout";
    }
    @GetMapping("/admin.html")
    public String getAdminPage(Model model)
    {
        return "admin";
    }

    @GetMapping("/author.html")
    public String getAuthorPage()
    {
        return "author";
    }
    @GetMapping("/favorites.html")
    public String getFavoritesPage()
    {
        return "favorites";
    }
    @GetMapping("/korzina.html")
    public String getKorzinaPage()
    {
        return "korzina";
    }
    @GetMapping("/log-reg.html")
    public String getLogRegPage()
    {
        return "log-reg";
    }
    @GetMapping("/profile.html")
    public String getProfilePage()
    {
        return "profile";
    }

}
