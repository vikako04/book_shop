package org.example.projectspring.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.projectspring.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional <MyUser> findByUsername(String username);
    MyUser findMyUserByUsername(String username);
    MyUser findMyUserById(Integer id);

}