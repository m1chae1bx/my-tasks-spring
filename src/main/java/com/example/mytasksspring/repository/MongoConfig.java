package com.example.mytasksspring.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Value("${spring.data.mongodb.authentication-database}")
  private String authSource;
  
  @Value("${spring.data.mongodb.host}")
  private String host;

  @Value("${spring.data.mongodb.port}")
  private String port;

  @Value("${spring.data.mongodb.username}")
  private String username;

  @Value("${spring.data.mongodb.password}")
  private String password;

  @Value("${spring.data.mongodb.database}")
  private String database;

  @Override
  public MongoClient mongoClient() {
    return MongoClients.create("mongodb://" + 
                                username + ":" + 
                                password + "@" + 
                                host + ":" + 
                                port + "/?authSource=" +
                                authSource);
  }

  @Override
  protected String getDatabaseName() {
    return "mytasksdb";
  }
}