package com.example.mytasksspring.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
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
  private LocalDateTime created;

  public Task(String name,
              Boolean completed,
              Date dueDate,
              String desc,
              LocalDateTime created) {
    this.name = name;
    this.completed = completed;
    this.dueDate = dueDate;
    this.desc = desc;
    this.created = created;
  }
}
