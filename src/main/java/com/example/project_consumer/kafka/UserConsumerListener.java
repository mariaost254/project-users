package com.example.project_consumer.kafka;
import com.example.project_consumer.dto.UserRequest;
import com.example.project_consumer.dto.UserStatusResponse;
import com.example.project_consumer.model.UserProject;
import com.example.project_consumer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserConsumerListener {

    private final UserRepository userRepository;
    private final SimpMessagingTemplate messagingTemplate;

    //TODO add retry policy
    @KafkaListener(topics = "user-events", groupId = "user-group")
    public void consume(UserRequest request) {
        try {
            if ("DELETE".equalsIgnoreCase(request.getAction())) {
                userRepository.findByEmail(request.getEmail())
                        .ifPresentOrElse(
                                user -> {
                                    userRepository.delete(user);
                                    log.info("User deleted sucesfully");
                                },
                                () -> log.info("User not found")
                        );
            } else if ("ADD".equalsIgnoreCase(request.getAction())) {
                boolean exists = userRepository.existsByEmail(request.getEmail());
                if (!exists) {
                    UserProject user = UserProject.builder()
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .id(UUID.randomUUID().toString())
                            .build();
                    userRepository.save(user);
                    log.info("User created");
                } else {
                    log.warn("User already exists");
                }
            }

            messagingTemplate.convertAndSend("/topic/user-status",
                    UserStatusResponse.builder()
                            .requestId(request.getRequestId())
                            .email(request.getEmail())
                            .action(request.getAction())
                            .build());

        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
            throw e;
        }
    }
}
