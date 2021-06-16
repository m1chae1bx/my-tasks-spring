package com.example.mytasksspring.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.mytasksspring.model.Task;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomTaskRepositoryImpl implements CustomTaskRepository {

  private MongoTemplate template;

  @Override
  public List<Task> findAllMatch(
    String name,
    String dateFilter,
    Date today,
    Boolean completed
  ) {

    Query query = new Query();
    if (name != null)
      query.addCriteria(Criteria.where("name").regex(name, "i"));
    if (completed != null)
      query.addCriteria(Criteria.where("completed").is(completed));
    if (dateFilter!= null && today != null) {
      Calendar c;
      Date refDate;
      switch(dateFilter) {
        case "today":
          query.addCriteria(Criteria.where("dueDate").is(today)); 
          break;
        case "tomorrow": 
          c = Calendar.getInstance();
          c.setTime(today);
          c.add(Calendar.DATE, 1);
          refDate = c.getTime(); 
          query.addCriteria(Criteria.where("dueDate").is(refDate)); 
          break;
        case "upcoming": 
          c = Calendar.getInstance();
          c.setTime(today);
          c.add(Calendar.DATE, 1);
          refDate = c.getTime(); 
          query.addCriteria(Criteria.where("dueDate").gt(refDate)); 
          break;
        case "overdue": 
          query.addCriteria(Criteria.where("dueDate").lt(today)); 
          break;
        case "unplanned": 
          query.addCriteria(Criteria.where("dueDate").is(null)); 
          break;
      }
    }

    return template.find(query, Task.class);

  }
  
  

}
