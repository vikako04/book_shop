package org.example.projectspring.repository;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Favorite;
import org.example.projectspring.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Favorite findByUserAndBook(MyUser user, Book book);

    List<Favorite> findByUserId(Integer id);

    void deleteByUserAndBook(MyUser user, Book book);
    Favorite save(Favorite favorite);

    boolean existsByUserAndBook(MyUser user, Book book);
}
