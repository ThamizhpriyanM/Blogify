package com.blogify.blogify.repository;

import com.blogify.blogify.entity.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends MongoRepository<BlogPost,String> {

}
