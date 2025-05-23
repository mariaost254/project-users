package com.example.project_consumer.controller;

import com.example.project_consumer.dto.UserDTOResponse;
import com.example.project_consumer.dto.UserRequest;
import com.example.project_consumer.dto.UserStatusResponse;
import com.example.project_consumer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserStatusResponse> addUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.addUser(userRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<UserStatusResponse> deleteUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.deleteUser(userRequest));
    }
}
