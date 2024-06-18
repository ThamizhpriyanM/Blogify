package com.blogify.blogify.repository;
import com.blogify.blogify.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

    public interface UserRepository extends MongoRepository<User, ObjectId> {
        User findByUsername(String username);
    }