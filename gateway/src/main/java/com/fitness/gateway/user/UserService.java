package com.fitness.gateway.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Qualifier("userServiceWebClient")
    private final WebClient userServiceWebClient;

    public Mono<Boolean> validateUser(String userId) {

        log.info("Calling user validation API for userId: {}", userId);

        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)

                // Handle errors properly
                .onErrorResume(WebClientResponseException.class, e -> {

                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

                        log.warn("User not found: {}", userId);

                        // User does not exist
                        return Mono.just(false);
                    }

                    else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {

                        log.error("Invalid userId: {}", userId);

                        return Mono.error(
                                new RuntimeException("Invalid userId: " + userId)
                        );
                    }

                    log.error("Unexpected error: {}", e.getMessage());

                    return Mono.error(
                            new RuntimeException("Unexpected error: " + e.getMessage())
                    );
                });
    }

    public Mono<UserResponse> registerUser(Registerrequest registerrequest) {

        log.info("Calling user registration for {}",
                registerrequest.getEmail());

        return userServiceWebClient.post()
                .uri("/api/users/register")
                .bodyValue(registerrequest)
                .retrieve()
                .bodyToMono(UserResponse.class)

                .onErrorResume(WebClientResponseException.class, e -> {

                    if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {

                        log.error("Bad request: {}", e.getMessage());

                        return Mono.error(
                                new RuntimeException("Bad Request: " + e.getMessage())
                        );
                    }

                    log.error("Unexpected error: {}", e.getMessage());

                    return Mono.error(
                            new RuntimeException("Unexpected error: " + e.getMessage())
                    );
                });
    }
}