package com.blogify.blogify.controller;

import com.blogify.blogify.entity.BlogPost;
import com.blogify.blogify.entity.User;
import com.blogify.blogify.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class adminController {
    BlogService blogService;

    @GetMapping("/getAlladmin")
    public String getAllPosts(Model model) {
        List<BlogPost> posts = blogService.getAllPosts();
        model.addAttribute("posts", posts);
        return "getAlladmin";
    }

    @PostMapping("/createadmin")
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        try {
            BlogPost newPost = blogService.createPost(blogPost);
            return new ResponseEntity<>(newPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateadmin/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable("id") String id, @RequestBody BlogPost blogPost) {
        Optional<BlogPost> postData = blogService.getPostByID(id);

        if (postData.isPresent()) {
            BlogPost existingPost = postData.get();
            existingPost.setTitle(blogPost.getTitle());
            existingPost.setContent(blogPost.getContent());
            existingPost.setAuthor(blogPost.getAuthor());
            return new ResponseEntity<>(blogService.updatePost(existingPost, id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/editadmin/{id}")
    public String editPostForm(@PathVariable("id") String id, Model model) {
        Optional<BlogPost> optionalPost = blogService.getPostByID(id);
        if (optionalPost.isPresent()) {
            model.addAttribute("post", optionalPost.get());
            return "updateadmin";
        } else {
            return "redirect:/getAlladmin";
        }
    }
    @DeleteMapping("/deleteadmin/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
        try {
            blogService.deletePost(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/{username}/posts")
    public String showPostsByAuthor(@PathVariable("username") String username, Model model) {
        List<BlogPost> posts = blogService.getPostsByAuthor(username);
        if (posts.isEmpty()) {
            model.addAttribute("errorMessage", "No posts found for author: " + username);
        }
        model.addAttribute("posts", posts);
        return "posts_by_author";
    }

}