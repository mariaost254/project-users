package com.example.project_consumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProject {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Id
    private String id;
    private String requestId;
    private String action;
}
