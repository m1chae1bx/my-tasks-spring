package com.example.mytasksspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import com.example.mytasksspring.model.ERole;
// import com.example.mytasksspring.model.Role;
// import com.example.mytasksspring.model.UserRepository;
// import com.example.mytasksspring.repository.RoleRepository;
// import java.time.LocalDateTime;
// import java.util.Date;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;

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
