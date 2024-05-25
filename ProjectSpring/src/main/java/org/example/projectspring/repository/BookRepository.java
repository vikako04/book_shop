package org.example.projectspring.repository;

import org.example.projectspring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorId(Integer authorId);
    Book findBookById(Integer id);
    @Query("SELECT DISTINCT b FROM Book b " +
            "LEFT JOIN b.categories c " +
            "WHERE (:priceFrom IS NULL OR b.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR b.price <= :priceTo) " +
            "AND (:authorIds IS NULL OR b.author.id IN :authorIds) " +
            "AND (COALESCE(:categoryIds, NULL) IS NULL OR c.id IN :categoryIds) " +
            "AND (:rating IS NULL OR b.rating >= :rating)")
    List<Book> findFilteredBooks(@Param("priceFrom") Integer priceFrom, @Param("priceTo") Integer priceTo,
                                 @Param("authorIds") List<Integer> authorIds, @Param("categoryIds") List<Integer> categoryIds,
                                 @Param("rating") Integer rating);
}

