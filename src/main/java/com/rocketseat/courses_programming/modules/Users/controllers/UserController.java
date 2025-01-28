package com.rocketseat.courses_programming.modules.Users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.courses_programming.exceptions.UserFoundException;
import com.rocketseat.courses_programming.modules.Users.UserEntity;
import com.rocketseat.courses_programming.modules.Users.dto.RequestUserDTO;
import com.rocketseat.courses_programming.modules.Users.dto.UpdateRequestDTO;
import com.rocketseat.courses_programming.modules.Users.useCases.CreateUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuário", description = "Informações do Usuário")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

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
    public ResponseEntity<Object> update(@Valid @RequestBody UpdateRequestDTO updateRequestDTO) {
        try{
            //use case de update
        }


        return null;
    }
}
