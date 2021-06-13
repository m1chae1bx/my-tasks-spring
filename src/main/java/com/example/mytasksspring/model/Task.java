package com.example.mytasksspring.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Task {
  @Id
  private String id;
  private String name;
  private Boolean completed;
  private Date dueDate;
  private String desc;
  @CreatedDate
  private Date createdAt;
  @LastModifiedDate
  private Date updatedAt;

  public Task(String name,
              Boolean completed,
              Date dueDate,
              String desc) {
    this.name = name;
    this.completed = completed;
    this.dueDate = dueDate;
    this.desc = desc;
  }
}
