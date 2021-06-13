package com.example.mytasksspring.repository;

import java.util.Optional;

import com.example.mytasksspring.model.ERole;
import com.example.mytasksspring.model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
