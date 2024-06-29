package com.blogify.blogify.controller;

import com.blogify.blogify.Dto.UserDTO;
import com.blogify.blogify.entity.BlogPost;
import com.blogify.blogify.entity.User;
import com.blogify.blogify.service.BlogService;
import com.blogify.blogify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        if (session.getAttribute("username") != null) {
            String role = (String) session.getAttribute("role");
            if ("admin".equals(role)) {
                return "redirect:/adminhome";
            } else {
                return "redirect:/home";
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session) {
        User user = userService.getUserByUsername(userDTO.getUsername());
        if (user != null && user.getPassword().equals(userDTO.getPassword()) && user.getRole().equals(userDTO.getRole())) {
            session.setAttribute("username", userDTO.getUsername());
            session.setAttribute("role", userDTO.getRole());
            if ("admin".equals(userDTO.getRole())) {
                return "redirect:/adminhome";
            } else {
                return "redirect:/home";
            }
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/create")
    public String createPage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "create";
    }

    @GetMapping("/update")
    public String updatePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "update";
    }

    @GetMapping("/delete")
    public String deletePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "delete";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/adminhome")
    public String admicreatePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "adminhome";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getUsersByRole("user");
        model.addAttribute("users", users);
        return "user_list";
    }


}
