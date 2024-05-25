package org.example.projectspring.controller;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.entity.Review;
import org.example.projectspring.service.BookService;
import org.example.projectspring.service.ReviewService;
import org.example.projectspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/addReview")
    public String addReview(@RequestParam("rating") int rating,
                            @RequestParam("content") String content,
                            @RequestParam("userId") Integer userId,
                            @RequestParam("bookId") Integer bookId) {

        MyUser user = userService.findById(userId);
        Book book = bookService.findById(bookId);
        if (!reviewService.isBookInFavorites(user, book)) {

            Review review = new Review();
            review.setUser(user);
            review.setBook(book);
            review.setRating(rating);
            review.setContent(content);

            reviewService.addReview(review);
        }
        else
        {
           Review review = reviewService.findByUserAndBook(user, book);
            review.setRating(rating);
            review.setContent(content);
            reviewService.addReview(review);
        }


        int avgRating = reviewService.calculateAverageRatingByBookId(bookId);
        book.setRating(avgRating);
        bookService.addBook(book);

        return "redirect:/main";
    }


}
