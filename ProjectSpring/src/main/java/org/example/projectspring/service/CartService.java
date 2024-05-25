package org.example.projectspring.service;

import org.example.projectspring.entity.Book;
import org.example.projectspring.entity.Cart;
import org.example.projectspring.entity.Favorite;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService
{
    @Autowired
    private CartRepository cartRepository;
    public void addToCart(MyUser user, Book book) {

        if (isBookInCart(user, book)) {

            return;
        }

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setBook(book);
        cartRepository.save(cart);
    }

    public void removeFromCart(MyUser user, Book book) {

        Cart cart = cartRepository.findByUserAndBook(user, book);
        if (cart != null) {
            cartRepository.delete(cart);
        }
    }

    public boolean isBookInCart(MyUser user, Book book) {

        return cartRepository.existsByUserAndBook(user, book);
    }

    public List<Book> findBooksByUserId(Integer id)
    {
        List<Cart> carts = cartRepository.findByUserId(id);

        List<Book> booksInCart = new ArrayList<>();
        for (Cart cart : carts) {
            booksInCart.add(cart.getBook());
        }

        return booksInCart;
    }
}
