package org.example.projectspring.controller;

import org.example.projectspring.entity.MyUser;
import org.example.projectspring.repository.MyUserRepository;
import org.example.projectspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class UserController {



//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/register")
//    public MyUser registerUser(@RequestParam("name") String name,
//                               @RequestParam("username") String username,
//                               @RequestParam("email") String email,
//                               @RequestParam("password") String password,
//                               @RequestParam("confirmPassword") String confirmPassword,
//                               @RequestParam("role") String role) {
//        if (!password.equals(confirmPassword)) {
//
//        }
//
//
//        String hashedPassword = passwordEncoder.encode(password);
//
//
//        MyUser user = new MyUser();
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(hashedPassword);
//        user.setRole(role);
//        user.setName(name);
//
//        if (userService.existsByUsername(username)) {
//
//        }
//        if (userService.existsByEmail(email)) {
//
//        }
//        return userService.saveUser(user);
//
//    }
@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyUser createUser(@RequestBody MyUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }
}
}