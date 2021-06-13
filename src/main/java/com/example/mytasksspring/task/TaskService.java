package com.example.mytasksspring.task;

import java.util.List;
import java.util.Optional;

import com.example.mytasksspring.model.Task;
import com.example.mytasksspring.repository.TaskRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskService {
  
  private final TaskRepository repository;

  public List<Task> getAll() {
    return repository.findAll();
  }

  public List<Task> getAllMatch(String name) {
    return repository.findAllMatch(name);
  }

  public Optional<Task> get(String id) {
    return repository.findById(id);
  }

  public Task create(Task task) {
    return repository.insert(task);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }

  public Task update(Task task) {
    Task savedTask = repository.findById(task.getId())
      .orElseThrow(() -> new RuntimeException(String.format("Cannot find task with id %s", task.getId())));

    if (task.getName() != null)
      savedTask.setName(task.getName());
    
    if (task.getDesc() != null)
      savedTask.setDesc(task.getDesc());
    
    if (task.getDueDate() != null)
      savedTask.setDueDate(task.getDueDate());
    
    if (task.getCompleted() != null)
      savedTask.setCompleted(task.getCompleted());

    return repository.save(savedTask);
  }

}
