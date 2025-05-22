package com.example.project_consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTOResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String id;
}
