package com.fitness.gateway;

import com.fitness.gateway.user.Registerrequest;
import com.fitness.gateway.user.UserService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserSyncFilter implements WebFilter {

    private final UserService userService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String userId = exchange.getRequest()
                .getHeaders()
                .getFirst("X-User-ID");

        String token = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        Registerrequest registerrequest = null;

        // Prevent NullPointerException
        if (token != null && token.startsWith("Bearer ")) {
            registerrequest = getUserDetails(token);
        }

        // If header missing, extract userId from token
        if (userId == null && registerrequest != null) {
            userId = registerrequest.getKeyCloakId();
        }

        // Continue normally if no authentication data
        if (userId == null || token == null) {
            return chain.filter(exchange);
        }

        String finalUserId = userId;
        Registerrequest finalRegisterrequest = registerrequest;

        return userService.validateUser(userId)
                .flatMap(exist -> {

                    if (!exist && finalRegisterrequest != null) {

                        log.info("Registering new user: {}", finalUserId);

                        return userService.registerUser(finalRegisterrequest)
                                .then();
                    }

                    log.info("User already exists, skipping sync");

                    return Mono.empty();
                })
                .then(chain.filter(exchange));
    }

    private Registerrequest getUserDetails(String token) {

        try {

            String tokenWithoutBearer = token
                    .replace("Bearer", "")
                    .trim();

            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);

            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            Registerrequest request = new Registerrequest();

            request.setEmail(claims.getStringClaim("email"));
            request.setKeyCloakId(claims.getStringClaim("sub"));
            request.setFirst_name(claims.getStringClaim("given_name"));
            request.setLast_name(claims.getStringClaim("family_name"));

            // Dummy password
            request.setPassword("dummy@123123");

            return request;

        } catch (ParseException e) {

            log.error("Error parsing JWT token", e);

            return null;
        }
    }
}