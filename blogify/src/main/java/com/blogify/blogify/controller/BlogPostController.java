package com.blogify.blogify.controller;

import com.blogify.blogify.entity.BlogPost;
import com.blogify.blogify.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class BlogPostController {
         BlogService blogService;

         @GetMapping("/getAll")
         public List<BlogPost> getAll(){
             return blogService.getAllPosts();
         }

         @PostMapping("/create")
         public BlogPost createPost(@RequestBody BlogPost blogPost){
             return blogService.createPost(blogPost);
         }

}
