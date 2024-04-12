package org.example.projectspring.service;

import org.example.projectspring.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService
{
    @Autowired
    private ReviewRepository reviewRepository;
}
