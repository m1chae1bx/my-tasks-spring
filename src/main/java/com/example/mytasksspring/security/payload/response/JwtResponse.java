package com.example.mytasksspring.security.payload.response;

import java.util.List;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponse {
  @NonNull
  private String token;
  private String type = "Bearer";
	@NonNull
  private String id;
  @NonNull
	private String username;
  @NonNull
	private String email;
	@NonNull
  private List<String> roles;
}
