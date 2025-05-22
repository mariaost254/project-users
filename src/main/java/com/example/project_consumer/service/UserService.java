package com.example.project_consumer.service;

import com.example.project_consumer.dto.UserDTOResponse;
import com.example.project_consumer.dto.UserRequest;
import com.example.project_consumer.dto.UserStatusResponse;
import com.example.project_consumer.model.UserProject;
import com.example.project_consumer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @SneakyThrows
    public List<UserDTOResponse> getAllUsers(){
        List<UserProject> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserDTOResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build())
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public UserStatusResponse addUser(UserRequest request) {
        String requestId = UUID.randomUUID().toString();
        boolean isExist = userRepository.existsByEmail(request.getEmail());
        if(!isExist) {
            request.setRequestId(requestId);
            kafkaTemplate.send( "user-events", request);
            return UserStatusResponse.builder()
                    .requestId(requestId)
                    .action("ADD")
                    .email(request.getEmail())
                    .build();
        } else {
        throw new IllegalArgumentException("User with email already exists");
        }
    }

    @SneakyThrows
    public UserStatusResponse deleteUser(UserRequest request) {
        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);

        kafkaTemplate.send( "user-events", request);
        return UserStatusResponse.builder()
                .requestId(requestId)
                .action("DELETE")
                .email(request.getEmail())
                .build();
    }
}
