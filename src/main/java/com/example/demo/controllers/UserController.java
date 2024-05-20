package com.example.demo.controllers;

import java.net.http.HttpClient.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.example.demo.models.User;
import com.example.demo.models.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestBody;



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

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletResponse response, HttpSession session) {
        // Requires the getting of the session_user attribute
        User user = (User)session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        }
        else {
            return "users/protected";
        }
    }
    
    
    // Occurs once the person attempts to sign in
    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> formData, Model model, HttpServletRequest request, HttpSession session) {
         //process
         String name = formData.get("name");
         String password = formData.get("password");

         List<User> userList = userRepo.findByNameAndPassword(name, password);
         if (userList.isEmpty()) {
            return "users/login";
         }
         else {
            // Success
            // Assuming name and passwords are unique
            User user = userList.get(0);
            request.getSession().setAttribute("session_user", user);
            model.addAttribute("user", user);
            return "users/protected";
         }
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/users/login";
    }

    @GetMapping("/")
    public RedirectView process() {
        return new RedirectView("login");
    }
    
}