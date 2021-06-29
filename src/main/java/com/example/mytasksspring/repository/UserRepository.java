package com.example.mytasksspring.repository;

import java.util.Optional;

import com.example.mytasksspring.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
  
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query(value = "{ username: ?0}", delete = true)
  void deleteByUsername(String username);

}
