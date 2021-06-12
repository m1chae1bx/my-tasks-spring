package com.example.mytasksspring;

// import java.time.LocalDateTime;
// import java.util.Date;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
}
