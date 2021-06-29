package com.example.mytasksspring.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.example.mytasksspring.model.User;
import com.example.mytasksspring.security.payload.request.LoginRequest;
import com.example.mytasksspring.security.payload.response.MessageResponse;
import com.example.mytasksspring.task.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
  private final UserService service;
  private AuthenticationManager authenticationManager;

  @GetMapping("{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Optional<User>> get(@PathVariable String id) {
    return ResponseEntity.ok(service.get(id));
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<MessageResponse> delete(@Valid @RequestBody LoginRequest loginRequest, @PathVariable String id) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);    

    service.delete(id);
    
    return ResponseEntity.ok().body(new MessageResponse("User has been deleted", null));
  }
  
  @PutMapping("{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<User> update(@RequestBody User user, @PathVariable String id) {
    user.setId(id);
    return ResponseEntity.ok(service.update(user));   // need to authorize also
  }
}
