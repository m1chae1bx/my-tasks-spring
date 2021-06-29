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

  public void delete(String id) {
    repository.deleteById(id);
  }

  public User update(User user) {
    User savedUser = repository.findById(user.getId())
      .orElseThrow(() -> new RuntimeException(String.format("Cannot find user with id %s", user.getId())));

      savedUser.setFullName(user.getFullName());
      savedUser.setNickname(user.getNickname());

    return repository.save(savedUser);
  }

}
