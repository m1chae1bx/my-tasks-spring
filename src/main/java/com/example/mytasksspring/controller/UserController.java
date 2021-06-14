package com.example.mytasksspring.controller;

import java.util.Optional;

import com.example.mytasksspring.model.User;
import com.example.mytasksspring.task.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
  private final UserService service;

  @GetMapping("{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Optional<User>> get(@PathVariable String id) {
    return ResponseEntity.ok(service.get(id));
  }
}
