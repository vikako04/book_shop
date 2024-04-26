package org.example.projectspring.service;

import org.example.projectspring.entity.MyUser;
import org.example.projectspring.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MyUserRepository userRepository;

    public MyUser findByUsernameAndPassword(String username, String password)
    {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    public Optional<MyUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public MyUser saveUser(MyUser user)
    {
        return userRepository.save(user);
    }


    public boolean existsByUsername(String username)
    {
        return userRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email)
    {
        return userRepository.existsByEmail(email);
    }

}
