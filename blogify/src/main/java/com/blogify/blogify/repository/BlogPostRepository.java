package com.blogify.blogify.repository;

import com.blogify.blogify.entity.BlogPost;
import com.blogify.blogify.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends MongoRepository<BlogPost,String> {
    List<BlogPost> findByAuthor(String author);
}
