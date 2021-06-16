package com.example.mytasksspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MyTasksSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyTasksSpringApplication.class, args);
  }

  // @Bean
  // CommandLineRunner runner(TaskRepository repository) {
  // 	return args -> {
  // 		Task task = new Task(
  // 			"Buy milk",
  // 			false,
  // 			new Date(),
  // 			"This is a sample description",
  // 			LocalDateTime.now()
  // 		);

  // 		repository.insert(task);
  // 	};
  // }

  // Run only when there's a new db
  // @Bean
  // CommandLineRunner runner(RoleRepository repository) {
  //   return args -> {
  //     repository.insert(new Role(ERole.ROLE_ADMIN));
  //     repository.insert(new Role(ERole.ROLE_USER));
  //   };
  // }
}
