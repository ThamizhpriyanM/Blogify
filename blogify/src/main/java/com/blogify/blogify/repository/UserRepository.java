package com.blogify.blogify.repository;
import com.blogify.blogify.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface UserRepository extends MongoRepository<User, String> {
        User findByUsername(String username);
        List<User> findByRole(String role);
}