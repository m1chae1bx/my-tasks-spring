package com.example.mytasksspring.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.mytasksspring.model.Task;
import com.example.mytasksspring.security.payload.response.MessageResponse;
import com.example.mytasksspring.task.TaskService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<Task>> getAllMatch(
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "dueDate", required = false) String dateFilter,
    @RequestParam(value = "today", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date today,
    @RequestParam(value = "completed", required = false) Boolean completed
  ) {
    return ResponseEntity.ok(service.getAllMatch(name, dateFilter, today, completed));
  }
  

  @GetMapping("{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Optional<Task>> get(@PathVariable String id) {
    return ResponseEntity.ok(service.get(id));
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Task> create(@RequestBody Task task) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(task));
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<MessageResponse> delete(@PathVariable String id) {
    service.delete(id);
    
    return ResponseEntity.ok().body(new MessageResponse("Task " + id + " has been deleted", "usernameUnavailable"));
  }

  @PutMapping
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Task> update(@RequestBody Task task) {
    return ResponseEntity.ok(service.update(task));   
  }

}
