package com.inv.spring.data.mongodb.repository;

import com.inv.spring.data.mongodb.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
