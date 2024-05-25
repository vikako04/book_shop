package org.example.projectspring.repository;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Cart;
import org.example.projectspring.entity.Favorite;
import org.example.projectspring.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserAndBook(MyUser user, Book book);

    List<Cart> findByUserId(Integer id);

    void deleteByUserAndBook(MyUser user, Book book);
    Cart save(Cart cart);

    boolean existsByUserAndBook(MyUser user, Book book);
}
