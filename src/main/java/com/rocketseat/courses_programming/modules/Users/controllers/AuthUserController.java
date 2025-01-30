package com.rocketseat.courses_programming.modules.Users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.courses_programming.modules.Users.dto.AuthUserRequestDTO;
import com.rocketseat.courses_programming.modules.Users.useCases.AuthUserUseCase;

@RestController
@RequestMapping("/user")
public class AuthUserController {
    @Autowired
    private AuthUserUseCase authUserUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthUserRequestDTO requestDTO) {
        try {
            System.out.printf("Sistema de Verificação Iniciadas: " + requestDTO.username() + "\n");
            System.out.println(requestDTO.password());
            var response = this.authUserUseCase.execute(requestDTO);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
