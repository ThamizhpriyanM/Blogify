package com.blogify.blogify.controller;

import com.blogify.blogify.Dto.UserDTO;
import com.blogify.blogify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

//    @PostMapping("/register")
//    public String register(UserDTO userDTO) {
//        userService.createUser(userDTO);
//        return "redirect:/login";
//    }


    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @GetMapping("/update")
    public String getAllPage() {
        return "update";
    }

    @GetMapping("/delete")
    public String deletePage() {
        return "delete";
    }


        @GetMapping("/logout")
        public String logout(HttpServletRequest request, HttpSession session) {
            session.invalidate();
            return "login";
    }

}