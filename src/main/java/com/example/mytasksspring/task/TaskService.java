package com.example.mytasksspring.task;

import java.util.Date;
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

  public List<Task> getAllMatch(
    String name,
    String dateFilter,
    Date today,
    Boolean completed
  ) {
    return repository.findAllMatch(name, dateFilter, today, completed);
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

      savedTask.setName(task.getName());
      savedTask.setDesc(task.getDesc());
      savedTask.setDueDate(task.getDueDate());
      savedTask.setCompleted(task.getCompleted());

    return repository.save(savedTask);
  }

}
