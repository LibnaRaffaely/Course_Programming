package com.rocketseat.courses_programming.modules.Users.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rocketseat.courses_programming.modules.Users.UserRepository;
import com.rocketseat.courses_programming.modules.Users.dto.AuthUserRequestDTO;
import com.rocketseat.courses_programming.modules.Users.dto.AuthUserResponseDTO;

@Service
public class AuthUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthUserResponseDTO execute(AuthUserRequestDTO authUserRequestDTO) throws AuthenticationException {
        // conferir a existência e se não tiver retornar um erro
        var user = this.userRepository.findByUsername(authUserRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        // verificar se a senha é igual
        var passwordMatches = this.passwordEncoder.matches(authUserRequestDTO.password(), user.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        // retornar o token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("Courses_ProgrammingAPI")
                .withSubject(user.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("USER_API"))
                .sign(algorithm);

        var response = AuthUserResponseDTO.builder()
                .token(token)
                .expires_in(expiresIn)
                .build();

        return response;
    }
}
