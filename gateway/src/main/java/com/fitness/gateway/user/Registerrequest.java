package com.fitness.gateway.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Registerrequest {
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required ")
     private String email;
     @Size(min = 6, message = "Password must be at least 6 characters")
     private String keyCloakId;
     @NotBlank(message = "Password is required")
     private String password;
     @NotBlank(message = "First Name is required")
     private String first_name;
     private String last_name;
}
