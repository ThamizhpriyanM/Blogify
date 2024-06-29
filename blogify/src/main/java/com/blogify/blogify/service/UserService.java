package com.blogify.blogify.service;

import com.blogify.blogify.Dto.UserDTO;
import com.blogify.blogify.entity.User;
import com.blogify.blogify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
    }
    public boolean verifyUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        return user != null && user.getPassword().equals(userDTO.getPassword()) && user.getRole().equals(userDTO.getRole());
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}