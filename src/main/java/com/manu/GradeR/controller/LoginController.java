package com.manu.GradeR.controller;

import com.manu.GradeR.model.User;
import com.manu.GradeR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody User user) {
        System.out.println("in methode");
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
