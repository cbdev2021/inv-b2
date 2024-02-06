package com.inv.spring.data.mongodb.repository;

import com.inv.spring.data.mongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
