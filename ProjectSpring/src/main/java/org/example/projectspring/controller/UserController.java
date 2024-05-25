package org.example.projectspring.controller;


import org.example.projectspring.entity.Cart;
import org.example.projectspring.entity.MyUser;
import org.example.projectspring.repository.CartRepository;
import org.example.projectspring.repository.MyUserRepository;
import org.example.projectspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private  UserService userService;


    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    public String createUser(@RequestParam("name") String name,
                               @RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("role") String role) {


        if (password.equals(confirmPassword))
        {
            MyUser newUser = new MyUser();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setRole(role);
            myUserRepository.save(newUser);
            return "log.html";
        }

        return "reg.html";
    }


    @PostMapping("/changePsw")
    public String changePsw(@RequestParam String currentPassword,
                            @RequestParam String newPassword,
                            @RequestParam String confirmPassword)
    {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);

        if (passwordEncoder.matches(currentPassword, user.getPassword()))
        {
            if (newPassword.equals(confirmPassword))
            {
                user.setPassword(passwordEncoder.encode(newPassword));
                myUserRepository.save(user);
            }
        }
        return "redirect:/profile";
    }
    @PostMapping("/changeEmail")
    public String changeEmail(@RequestParam String currentEmail,
                            @RequestParam String newEmail)
    {
        String userName = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);

            if (currentEmail.equals(user.getEmail()))
            {
                user.setEmail(newEmail);
                myUserRepository.save(user);
            }

        return "redirect:/profile";
    }


    @PostMapping("/addBalance")
    public String addBalance(@RequestParam int amount)
    {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);
        if (amount>0)
        {
            int newBalance = (user.getBalance()+amount);
            user.setBalance(newBalance);
            myUserRepository.save(user);
        }
        if(user.getRole().equals("ADMIN"))
            return "redirect:/admin";
        return "redirect:/profile";
    }

    @PostMapping("/buyBooks")
    public String buyBooks(@RequestParam int from, @RequestParam int to, @RequestParam int totalAmount)
    {
        String userName =  SecurityContextHolder.getContext()
                .getAuthentication().getName();

        MyUser user = userService.findMyUserByUsername(userName);


        if (userService.transferMoney(from, to, totalAmount))
        {
            List<Cart> carts = cartRepository.findByUserId(from);

            for (Cart cart : carts) {
                cartRepository.delete(cart);
            }

        }


        if(user.getRole().equals("ADMIN"))
            return "redirect:/admin";
        return "redirect:/profile";
    }

}