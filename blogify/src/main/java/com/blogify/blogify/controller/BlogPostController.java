package com.blogify.blogify.controller;

import com.blogify.blogify.entity.BlogPost;
import com.blogify.blogify.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BlogPostController {

    private final BlogService blogService;

    @GetMapping("/getAll")
    public String getAllPosts(Model model) {
        List<BlogPost> posts = blogService.getAllPosts();
        model.addAttribute("posts", posts);
        return "getAll";
    }

    @PostMapping("/create")
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost, HttpSession session) {
        try {
            blogPost.setAuthor(getCurrentUsername(session)); // Set the current user as the author
            BlogPost newPost = blogService.createPost(blogPost);
            return new ResponseEntity<>(newPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable("id") String id) {
        Optional<BlogPost> postData = blogService.getPostByID(id);

        if (postData.isPresent()) {
            return new ResponseEntity<>(postData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable("id") String id, @RequestBody BlogPost blogPost, HttpSession session,RedirectAttributes redirectAttributes) {
        Optional<BlogPost> postData = blogService.getPostByID(id);

        if (postData.isPresent()) {
            BlogPost existingPost = postData.get();
            if (existingPost.getAuthor().equals(getCurrentUsername(session))) {
                existingPost.setTitle(blogPost.getTitle());
                existingPost.setContent(blogPost.getContent());
                return new ResponseEntity<>(blogService.updatePost(existingPost, id), HttpStatus.OK);
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "You can't edit other person's post.");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Post not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable("id") String id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
        Optional<BlogPost> optionalPost = blogService.getPostByID(id);
        if (optionalPost.isPresent()) {
            BlogPost post = optionalPost.get();
            if (post.getAuthor().equals(getCurrentUsername(session))) {
                model.addAttribute("post", post);
                return "update";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "You can't edit other person's post.");
                return "redirect:/getAll?error=permission_denied";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Post not found.");
            return "redirect:/getAll?error=not_found";
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id, HttpSession session, RedirectAttributes redirectAttributes) {
        Optional<BlogPost> postData = blogService.getPostByID(id);

        if (postData.isPresent()) {
            BlogPost post = postData.get();
            if (post.getAuthor().equals(getCurrentUsername(session))) {
                blogService.deletePost(id);
                return ResponseEntity.noContent().build();
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "You can't delete other person's post.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Post not found.");
            return ResponseEntity.notFound().build();
        }
    }

    private String getCurrentUsername(HttpSession session) {
        return (String) session.getAttribute("username");
    }
}
