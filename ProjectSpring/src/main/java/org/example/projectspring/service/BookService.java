package org.example.projectspring.service;

import org.example.projectspring.entity.Book;
import org.example.projectspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public Book findById(Integer id)
    {
        return bookRepository.findBookById(id);
    }


    public int findMinPrice()
    {
        List<Book> books = getAllBooks();
        List<Integer> prices = new ArrayList<>();

        for (Book book: books)
        {
            prices.add(book.getPrice());
        }
        int minPrice = Collections.min(prices);
        return minPrice;
    }
    public int findMaxPrice()
    {
        List<Book> books = getAllBooks();
        List<Integer> prices = new ArrayList<>();

        for (Book book: books)
        {
            prices.add(book.getPrice());
        }
        int maxPrice = Collections.max(prices);
        return maxPrice;
    }

    public List<Book> filterBooks(Integer priceFrom, Integer priceTo, List<Integer> authorIds,
                                  List<Integer> categoryIds, Integer rating) {

        return bookRepository.findFilteredBooks(priceFrom, priceTo, authorIds, categoryIds, rating);
    }
}
