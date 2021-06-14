package com.example.mytasksspring.task;

import java.util.Optional;

import com.example.mytasksspring.model.User;
import com.example.mytasksspring.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;

  public Optional<User> get(String id) {
    return repository.findById(id);
  }

}
