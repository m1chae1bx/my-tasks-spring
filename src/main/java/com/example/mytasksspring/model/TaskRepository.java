package com.example.mytasksspring.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String>, CustomTaskRepository {
  
}
