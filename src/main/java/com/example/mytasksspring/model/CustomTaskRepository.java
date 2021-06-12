package com.example.mytasksspring.model;

import java.util.List;

public interface CustomTaskRepository {
  
  List<Task> findAllMatch(String name);

}
