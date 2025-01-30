package com.rocketseat.courses_programming.modules.Users.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTServiceUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    public Optional<String> extractId(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            var tokenDecoded = JWT.require(algorithm)
                    .withIssuer("Courses_ProgrammingAPI")
                    .build()
                    .verify(token);
            return Optional.of(tokenDecoded.getSubject());

        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
