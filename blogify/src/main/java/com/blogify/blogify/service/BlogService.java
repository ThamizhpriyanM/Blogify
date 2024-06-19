package com.blogify.blogify.service;

import com.blogify.blogify.entity.BlogPost;
import com.blogify.blogify.repository.BlogPostRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {
    BlogPostRepository blogPostRepository;

    public List<BlogPost>getAllPosts(){
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost>getPostByID(String id){
        return blogPostRepository.findById(id);
    }

    public BlogPost createPost(BlogPost blogPost){
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updatePost(BlogPost blogPost,String id){
        Optional<BlogPost> optionalPost = blogPostRepository.findById(id);
        if(optionalPost.isPresent()){
            BlogPost existingPost= optionalPost.get();
            existingPost.setTitle(blogPost.getTitle());
            existingPost.setContent(blogPost.getContent());
            existingPost.setAuthor(blogPost.getAuthor());
            return blogPostRepository.save(existingPost);
        }else{
            throw new RuntimeException("post not found with id:"+ id);
        }
    }

    public void deletePost(String id){
        blogPostRepository.deleteById(id);
    }
}
