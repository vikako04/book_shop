package org.example.projectspring.service;

import org.example.projectspring.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService
{
    @Autowired
    private FavoriteRepository favoriteRepository;
}
