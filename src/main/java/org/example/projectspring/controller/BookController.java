package org.example.projectspring.controller;

import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Category;
import org.example.projectspring.service.AuthorService;
import org.example.projectspring.service.BookService;
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
public class BookController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;



    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/addBook")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("author") Integer authorId,
                          @RequestParam("pages") int numberOfPages,
                          @RequestParam("price") int price,
                          @RequestParam("quantity") int availability,
                          @RequestParam("image") MultipartFile image,
                          @RequestParam("category") List<Integer> categoryIds)
    {



        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setNumberOfPages(numberOfPages);
        book.setPrice(price);
        book.setAvailability(availability);


        if (image!=null)
        {
            String fileName = image.getOriginalFilename();
            String filePath = uploadPath + "/" + fileName;
            String imagePath = "/assets/images/"+fileName;
            try
            {
                image.transferTo(new File(filePath));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }


            book.setImage(imagePath);
        }

        Author author = authorService.getAuthorById(authorId);
        if (author != null)
        {
            book.setAuthor(author);
        }

        List<Category> categories = categoryService.findByIdIn(categoryIds);
        book.setCategories(categories);


        bookService.addBook(book);

        return "redirect:/admin-func.html";
    }
}
