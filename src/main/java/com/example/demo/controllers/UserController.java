package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.demo.models.User;

@Controller
public class UserController {
    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        System.out.println("Getting all users");
        //TODO get users from data base

        List<User> userlist = new ArrayList<>();
        userlist.add(new User("Xander", "Password", 25));
        userlist.add(new User("Ava", "Incorrect", 14));
        userlist.add(new User("Joshua", "Jerma", 985));
        userlist.add(new User("Colby", "COLBS$420$", 2));
        // End of database call
        model.addAttribute("us", userlist);
        return "users/showAll";
    }
    
}