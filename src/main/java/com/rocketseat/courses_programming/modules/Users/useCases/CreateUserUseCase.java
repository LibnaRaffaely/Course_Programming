package com.rocketseat.courses_programming.modules.Users.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.exceptions.UserFoundException;
import com.rocketseat.courses_programming.modules.Users.UserEntity;
import com.rocketseat.courses_programming.modules.Users.UserRepository;
import com.rocketseat.courses_programming.modules.Users.dto.RequestUserDTO;
import com.rocketseat.courses_programming.modules.Users.dto.ResponseUserDTO;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseUserDTO execute(RequestUserDTO requestUserDTO) {
        this.userRepository.findByUsernameOrEmail(requestUserDTO.getUsername(), requestUserDTO.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = this.passwordEncoder.encode(requestUserDTO.getPassword());
        requestUserDTO.setPassword(password);

        // torna-lo uma entity
        UserEntity userEntity = new UserEntity();
        userEntity.setName(requestUserDTO.getName());
        userEntity.setUsername(requestUserDTO.getUsername());
        userEntity.setPassword(requestUserDTO.getPassword());
        userEntity.setEmail(requestUserDTO.getEmail());

        this.userRepository.save(userEntity);

        var response = ResponseUserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .createdAt(userEntity.getCreatedAt())
                .build();

        return response;
    }
}
