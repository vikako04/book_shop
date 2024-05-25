package org.example.projectspring.service;

import jakarta.transaction.Transactional;
import org.example.projectspring.entity.BookStoreAccount;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.repository.BookStoreAccountRepository;
import org.example.projectspring.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private BookStoreAccountRepository bookStoreAccountRepository;

    public MyUser findMyUserByUsername(String username)
    {
        return userRepository.findMyUserByUsername(username);
    }


    public MyUser findById(Integer id)
    {
       return userRepository.findMyUserById(id);
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

    @Transactional
    public boolean transferMoney(int from, int to, int amount)
    {
        MyUser userFrom = userRepository.findMyUserById(from).get();
        BookStoreAccount bookStoreAccountTo = bookStoreAccountRepository.findBookStoreAccountById(to).get();

        if (userFrom.getBalance() >= amount)
        {
            userFrom.setBalance(userFrom.getBalance()-amount);
            userRepository.save(userFrom);


            bookStoreAccountTo.setBalance(bookStoreAccountTo.getBalance()+amount);
            bookStoreAccountRepository.save(bookStoreAccountTo);

            return true;
        }

        return false;
    }

}
