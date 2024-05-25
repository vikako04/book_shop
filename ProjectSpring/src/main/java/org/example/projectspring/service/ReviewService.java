package org.example.projectspring.service;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.entity.Review;
import org.example.projectspring.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService
{
    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Review review)
    {
        reviewRepository.save(review);
    }
    public List<Review> findByBookId(Integer bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public boolean isBookInFavorites(MyUser user, Book book) {

        return reviewRepository.existsByUserAndBook(user, book);
    }
    public Review findByUserAndBook(MyUser user, Book book)
    {
        return reviewRepository.findByUserAndBook(user, book);
    }

    public int calculateAverageRatingByBookId(int bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        if (reviews.isEmpty()) {
            return 0;
        } else {
            double totalRating = 0.0;
            for (Review review : reviews) {
                totalRating += review.getRating();
            }
            double averageRating = totalRating / reviews.size();
            return (int) Math.round(averageRating);
        }
    }

}
