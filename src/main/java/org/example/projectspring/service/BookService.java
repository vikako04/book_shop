package org.example.projectspring.service;

import org.example.projectspring.entity.Book;
import org.example.projectspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book)
    {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(Integer authorId)
    {
        return bookRepository.findByAuthorId(authorId);
    }

    public Book getBookById(int id)
    {
        return bookRepository.findById(id).orElseThrow();
    }

}
