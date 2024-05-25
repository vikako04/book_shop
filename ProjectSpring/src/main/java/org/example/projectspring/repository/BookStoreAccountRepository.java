package org.example.projectspring.repository;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.BookStoreAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreAccountRepository extends JpaRepository<BookStoreAccount, Integer> {
    BookStoreAccount findBookStoreAccountById(Integer id);
}
