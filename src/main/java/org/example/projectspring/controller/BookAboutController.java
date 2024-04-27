package org.example.projectspring.controller;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Category;
import org.example.projectspring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/book/{id}")
    public String getBookAboutPage(@PathVariable("id") Integer id, Model model)
    {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        List<Category> categories = book.getCategories();
        model.addAttribute("categories", categories);
        return "bookabout";
    }
}
