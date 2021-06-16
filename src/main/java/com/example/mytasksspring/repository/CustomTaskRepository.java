package com.example.mytasksspring.repository;

import java.util.Date;
import java.util.List;

import com.example.mytasksspring.model.Task;

public interface CustomTaskRepository {
  
  List<Task> findAllMatch(
    String name,
    String dateFilter,
    Date today,
    Boolean completed
  );

}
