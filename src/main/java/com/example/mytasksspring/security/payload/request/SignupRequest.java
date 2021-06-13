package com.example.mytasksspring.security.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank(message = "Full name is required")
  @Size(max = 200)
  private String fullName;
  
  @NotBlank(message = "Nickname is required")
  @Size(max = 20)
  private String nickname;

  @NotBlank(message = "Username is required")
  @Size(min = 6, max = 30)
  @Pattern(regexp = "^[A-Za-z0-9.]*$")
  private String username;
  
  @NotBlank(message = "Email is required")
  @Size(max = 100)
  @Email
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 20)
  @Pattern(regexp = "^(?=[^A-Za-z]*[A-Za-z])(?=[^0-9]*[0-9])(?=[^@#$%&!_:\\-.]*[@#$%&!_:\\-.]).*$")
  private String password;

  private Set<String> roles;
}
