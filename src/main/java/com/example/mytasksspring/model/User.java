package com.example.mytasksspring.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {
  @Id
  private String id;
  
  @NotBlank(message = "Username is required")
  @Size(max = 30)
  private String username;
  
  @NotBlank(message = "Email is required")
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank(message = "Password is required")
  @Size(max = 50)
  private String password;

  @DBRef
  private Set<Role> roles = new HashSet<>();

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
  
}
