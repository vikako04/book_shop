package org.example.projectspring.service;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Favorite;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService
{
    @Autowired
    private FavoriteRepository favoriteRepository;
    public void addToFavorites(MyUser user, Book book) {

        if (isBookInFavorites(user, book)) {

            return;
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setBook(book);
        favoriteRepository.save(favorite);
    }

    public void removeFromFavorites(MyUser user, Book book) {

        Favorite favorite = favoriteRepository.findByUserAndBook(user, book);
        if (favorite != null) {
            favoriteRepository.delete(favorite);
        }
    }

    public boolean isBookInFavorites(MyUser user, Book book) {

        return favoriteRepository.existsByUserAndBook(user, book);
    }

    public List<Book> findBooksByUserId(Integer id)
    {
        List<Favorite> favorites = favoriteRepository.findByUserId(id);

        List<Book> favoriteBooks = new ArrayList<>();
        for (Favorite favorite : favorites) {
            favoriteBooks.add(favorite.getBook());
        }

        return favoriteBooks;
    }
}
