package org.example.projectspring.repository;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByBookId(Integer bookId);
    boolean existsByUserAndBook(MyUser user, Book book);

    Review findByUserAndBook(MyUser user, Book book);

}
