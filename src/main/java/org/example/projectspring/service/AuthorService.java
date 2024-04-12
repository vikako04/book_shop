package org.example.projectspring.service;

import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.AuthorCategory;
import org.example.projectspring.entity.Category;
import org.example.projectspring.repository.AuthorRepository;
import org.example.projectspring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorCategoryService authorCategoryService;

    public void addAuthor(Author author)
    {
        authorRepository.save(author);
    }

    public List<Author> getAllAuthors()
    {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int authorId)
    {
        return authorRepository.findById(authorId).orElseThrow();
    }

}
