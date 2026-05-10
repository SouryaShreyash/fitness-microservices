package com.fitness.userservice.service;

import com.fitness.userservice.dto.Registerrequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Userservice {
    @Autowired
    private UserRepository repository;

    public UserResponse register( Registerrequest request) {
        if (repository.existsByEmail(request.getEmail())){
            User existingUser=repository.findByEmail(request.getEmail());
            UserResponse userResponse=new UserResponse();
            userResponse.setId(existingUser.getId());
            userResponse.setEmail(existingUser.getEmail());
            userResponse.setPassword(existingUser.getPassword());
            userResponse.setFirst_name(existingUser.getFirst_name());
            userResponse.setLast_name(existingUser.getLast_name());
            userResponse.setCreated_at(existingUser.getCreated_at());
            userResponse.setUpdated_at(existingUser.getUpdated_at());

            return userResponse;
        }
        User user=new User();
        user.setEmail(request.getEmail());
        user.setKeyCloakId(request.getKeyCloakId());
        user.setPassword(request.getPassword());
        user.setFirst_name(request.getFirst_name());
        user.setLast_name(request.getLast_name());

        User savedUser=repository.save(user);
        UserResponse userResponse=new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setKeyCloakId(savedUser.getKeyCloakId());
        userResponse.setPassword(savedUser.getPassword());
        userResponse.setFirst_name(savedUser.getFirst_name());
        userResponse.setLast_name(savedUser.getLast_name());
        userResponse.setCreated_at(savedUser.getCreated_at());
        userResponse.setUpdated_at(savedUser.getUpdated_at());

        return userResponse;
    }

    public UserResponse getUserProfile(String userId) {
        User user=repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirst_name(user.getFirst_name());
        userResponse.setLast_name(user.getLast_name());
        userResponse.setCreated_at(user.getCreated_at());
        userResponse.setUpdated_at(user.getUpdated_at());

        return userResponse;
    }

    public Boolean existByUserId(String userId) {
        log.info("Calling for user validation user API for userId: {}",userId);
        return repository.existsByKeyCloakId(userId);
    }
}
