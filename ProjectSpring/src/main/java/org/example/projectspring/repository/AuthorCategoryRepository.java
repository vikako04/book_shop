package org.example.projectspring.repository;

import org.example.projectspring.entity.AuthorCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorCategoryRepository extends JpaRepository<AuthorCategory, Integer> {
}
