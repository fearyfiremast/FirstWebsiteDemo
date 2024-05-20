package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Getting all users");
        List<User> userlist = userRepo.findAll();
                // End of database call
        model.addAttribute("us", userlist);
        return "users/showAll";
    }
    
    @PostMapping("/users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("ADD USER");
        String newName = newuser.get("name");
        String newPassword = newuser.get("password");
        int newsize = Integer.parseInt(newuser.get("size"));
        userRepo.save(new User(newName, newPassword, newsize));

        response.setStatus(201);

        return "users/addedUser";
    }
    
}