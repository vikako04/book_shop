package org.example.projectspring.service;

import org.example.projectspring.entity.Author;
import org.example.projectspring.entity.AuthorCategory;
import org.example.projectspring.entity.Category;
import org.example.projectspring.repository.AuthorCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorCategoryService
{
    @Autowired
    private AuthorCategoryRepository authorCategoryRepository;

    public void addAuthorCategory(Author author, Category category)
    {
        AuthorCategory authorCategory = new AuthorCategory();
        authorCategory.setAuthor(author);
        authorCategory.setCategory(category);
        authorCategoryRepository.save(authorCategory);
    }
}
