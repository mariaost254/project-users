package com.example.project_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ProjectConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectConsumerApplication.class, args);
	}

}
