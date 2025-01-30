package com.rocketseat.courses_programming.modules.Users.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocketseat.courses_programming.modules.Users.UserRepository;
import com.rocketseat.courses_programming.modules.Users.dto.ResponseUserDTO;
import com.rocketseat.courses_programming.modules.Users.dto.UpdateRequestDTO;

@Service
public class UpdateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    public ResponseUserDTO execute(UUID id, UpdateRequestDTO updateRequestDTO) {
        // verificar exixtência
        var user = this.userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        if (updateRequestDTO.getUsername() != null) {
            user.setUsername(updateRequestDTO.getUsername());
        }

        if (updateRequestDTO.getPassword() != null) {
            user.setPassword(updateRequestDTO.getPassword());
        }

        if (updateRequestDTO.getEmail() != null) {
            user.setEmail(updateRequestDTO.getEmail());
        }

        this.userRepository.save(user);

        var response = ResponseUserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();

        return response;
    }
}
