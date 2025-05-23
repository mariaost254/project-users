package com.example.project_consumer.config;

import com.example.project_consumer.model.UserProject;
import com.example.project_consumer.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInsert implements CommandLineRunner {
    private final UserRepository userRepository;

    public DataInsert(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.saveAll(List.of(
                    UserProject.builder().firstName("first").lastName("last").email("email@email.com").password("password").build(),
                    UserProject.builder().firstName("first").lastName("last").email("email2@email2.com").password("password").build()
            ));
        }
    }
}