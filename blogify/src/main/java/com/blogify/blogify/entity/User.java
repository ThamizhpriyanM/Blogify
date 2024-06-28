package com.blogify.blogify.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authenticate")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String[] roles;


}
