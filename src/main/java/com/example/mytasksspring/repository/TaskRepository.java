package com.example.mytasksspring.repository;

import com.example.mytasksspring.model.Task;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String>, CustomTaskRepository {
  
}
