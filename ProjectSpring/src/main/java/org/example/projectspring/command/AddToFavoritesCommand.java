package org.example.projectspring.command;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.service.FavoriteService;

public class AddToFavoritesCommand implements Command {
    private FavoriteService favoriteService;
    private MyUser user;
    private Book book;

    public AddToFavoritesCommand(FavoriteService favoriteService, MyUser user, Book book) {
        this.favoriteService = favoriteService;
        this.user = user;
        this.book = book;
    }

    @Override
    public void execute() {
        favoriteService.addToFavorites(user, book);
    }
}