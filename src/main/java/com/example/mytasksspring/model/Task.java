package com.example.mytasksspring.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document
public class Task {
  @Id
  private String id;
  private String name;
  private Boolean completed;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date dueDate;
  private String desc;
  @CreatedDate
  private LocalDateTime createdAt;
  @LastModifiedDate
  private LocalDateTime updatedAt;

  public Task(String name,
              Boolean completed,
              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
              Date dueDate,
              String desc) {
    this.name = name;
    this.completed = completed != null ? completed : false;
    this.dueDate = dueDate;
    this.desc = desc;
  }
}
