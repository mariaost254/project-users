package com.example.project_consumer.repository;

import com.example.project_consumer.model.UserProject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserProject, String> {
    boolean existsByEmail(String email);
    Optional<UserProject> findByEmail(String email);

}
