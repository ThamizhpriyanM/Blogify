package com.blogify.blogify.controller;

import com.blogify.blogify.entity.BlogPost;
import com.blogify.blogify.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class BlogPostController {
         BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        try {
            List<BlogPost> posts = blogService.getAllPosts();
            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        try {
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

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable("id") String id, @RequestBody BlogPost blogPost) {
        Optional<BlogPost> postData = blogService.getPostByID(id);

        if (postData.isPresent()) {
            BlogPost existingPost = postData.get();
            existingPost.setTitle(blogPost.getTitle());
            existingPost.setContent(blogPost.getContent());
            existingPost.setAuthor(blogPost.getAuthor());
            return new ResponseEntity<>(blogService.updatePost(existingPost,id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@DeleteMapping("/{id}")
public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") String id) {
    try {
        blogService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
