package com.rocketseat.courses_programming.modules.Users.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.courses_programming.exceptions.UserFoundException;
import com.rocketseat.courses_programming.modules.Users.dto.RequestUserDTO;
import com.rocketseat.courses_programming.modules.Users.dto.UpdateRequestDTO;
import com.rocketseat.courses_programming.modules.Users.useCases.ContractCourseUseCase;
import com.rocketseat.courses_programming.modules.Users.useCases.CreateUserUseCase;
import com.rocketseat.courses_programming.modules.Users.useCases.JWTServiceUseCase;
import com.rocketseat.courses_programming.modules.Users.useCases.UpdateUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuário", description = "Informações do Usuário")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Autowired
    private JWTServiceUseCase jwtServiceUseCase;

    @Autowired
    private ContractCourseUseCase contractCourseUseCase;

    @PostMapping("/")
    @Operation(summary = "Cadastro Usuário", description = " Essa função é responsável por criar o Usuário no sistema")
    public ResponseEntity<Object> create(@Valid @RequestBody RequestUserDTO requestUserDTO) {
        try {
            var responseUser = this.createUserUseCase.execute(requestUserDTO);
            return ResponseEntity.ok().body(responseUser);
        } catch (UserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // atualizar dados cadastrais
    @PostMapping("/update")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> update(@RequestHeader("Authorization") String auth,
            @RequestBody UpdateRequestDTO updateRequestDTO) {

        try {

            String token = auth.replace("Bearer ", "");
            System.out.println(token);
            var userIdOptional = this.jwtServiceUseCase.extractId(token);
            if (userIdOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido!");
            }

            UUID userId = UUID.fromString(userIdOptional.get());

            var response = this.updateUserUseCase.execute(userId, updateRequestDTO);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // Obter curso
    // O que é melhor? Get passando por PathVariable ou um post recebendo o id por
    // body?
    @GetMapping("/{courseId}")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> contractCourse(@PathVariable UUID courseId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getCredentials() instanceof String) {
                String token = (String) authentication.getCredentials();
                var userIdOptional = this.jwtServiceUseCase.extractId(token);

                if (userIdOptional.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido!");
                }

                UUID userId = UUID.fromString(userIdOptional.get());
                var contracted = this.contractCourseUseCase.execute(userId, courseId);
                if (contracted == false) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course cant be contracted");
                }
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
