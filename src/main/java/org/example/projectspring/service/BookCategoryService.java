package org.example.projectspring.service;

import org.example.projectspring.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryService
{
    @Autowired
    private BookCategoryRepository bookCategoryRepository;
}
