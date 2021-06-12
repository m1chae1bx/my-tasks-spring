package com.example.mytasksspring.model;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomTaskRepositoryImpl implements CustomTaskRepository {

  private MongoTemplate template;

  @Override
  public List<Task> findAllMatch(String name) {

    Query query = new Query();
    if (name != null)
      query.addCriteria(Criteria.where("name").regex(name, "i"));

    return template.find(query, Task.class);

  }
  
  

}
