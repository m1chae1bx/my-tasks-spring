package com.example.mytasksspring.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.mytasksspring.model.ERole;
import com.example.mytasksspring.model.Role;
import com.example.mytasksspring.model.User;
import com.example.mytasksspring.repository.RoleRepository;
import com.example.mytasksspring.repository.UserRepository;
import com.example.mytasksspring.security.UserDetailsImpl;
import com.example.mytasksspring.security.jwt.JwtUtils;
import com.example.mytasksspring.security.payload.request.LoginRequest;
import com.example.mytasksspring.security.payload.request.SignupRequest;
import com.example.mytasksspring.security.payload.response.JwtResponse;
import com.example.mytasksspring.security.payload.response.MessageResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
  AuthenticationManager authenticationManager;
  UserRepository userRepository;
  RoleRepository roleRepository;
  PasswordEncoder passwordEncoder;
  JwtUtils jwtUtils;

  @PostMapping("login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);    

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String jwt = jwtUtils.generateJwtToken(userDetails.getUsername(), userDetails.getId());
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());

    return ResponseEntity.ok(
      new JwtResponse(
        jwt, 
        userDetails.getId(), 
        userDetails.getUsername(), 
        userDetails.getEmail(), 
        roles
      )
    );
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new MessageResponse("Error: Email is already in use!", "emailUnavailable"));
    }

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new MessageResponse("Error: Username is already taken!", "usernameUnavailable"));
    }

    // Create new user's account
    User user = new User(
      signUpRequest.getFullName(),
      signUpRequest.getNickname(),
      signUpRequest.getUsername(), 
      signUpRequest.getEmail(),
      passwordEncoder.encode(signUpRequest.getPassword())
    );

    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    String jwt = jwtUtils.generateJwtToken(user.getUsername(), user.getId());
    List<String> roleList = user.getRoles().stream()
      .map(item -> item.getName().name())
      .collect(Collectors.toList());

    return ResponseEntity.ok(
      new JwtResponse(
        jwt, 
        user.getId(), 
        user.getUsername(), 
        user.getEmail(), 
        roleList
      )
    );
  }
}
