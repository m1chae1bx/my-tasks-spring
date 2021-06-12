package com.example.mytasksspring.controller;

import java.util.List;
import java.util.Optional;

import com.example.mytasksspring.model.Task;
import com.example.mytasksspring.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/tasks")
@AllArgsConstructor
public class TaskController {
  
  private final TaskService service;

  /** Already done by @AllArgsConstructor */
  // public TaskController(TaskService service) {
  //   this.service = service;
  // }

  // @GetMapping
  // public ResponseEntity<List<Task>> getAll() {
  //   return ResponseEntity.ok(service.getAll());
  // }

  @GetMapping
  public ResponseEntity<List<Task>> getAllMatch(
    @RequestParam(value = "name", required = false) String name
  ) {
    return ResponseEntity.ok(service.getAllMatch(name));
  }
  

  @GetMapping("{id}")
  public ResponseEntity<Optional<Task>> get(@PathVariable String id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping
  public ResponseEntity<Task> create(@RequestBody Task task) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(task));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.ok("Task " + id + "has been deleted");
  }

  @PutMapping
  public ResponseEntity<Task> update(@RequestBody Task task) {
    return ResponseEntity.ok(service.update(task));   
  }

}
