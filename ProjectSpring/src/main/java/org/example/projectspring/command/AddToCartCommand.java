package org.example.projectspring.command;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.service.CartService;

public class AddToCartCommand implements Command {
    private CartService cartService;
    private MyUser user;
    private Book book;

    public AddToCartCommand(CartService cartService, MyUser user, Book book) {
        this.cartService = cartService;
        this.user = user;
        this.book = book;
    }

    @Override
    public void execute() {
        cartService.addToCart(user, book);
    }
}