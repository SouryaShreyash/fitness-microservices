package com.fitness.gateway.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String Id;
    private String keyCloakId;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
