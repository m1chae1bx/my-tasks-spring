package com.example.mytasksspring.security.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
  private String message;
  private String code;
}
