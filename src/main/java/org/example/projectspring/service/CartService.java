package org.example.projectspring.service;

import org.example.projectspring.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService
{
    @Autowired
    private CartRepository cartRepository;
}
