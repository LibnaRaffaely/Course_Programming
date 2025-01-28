package com.rocketseat.courses_programming.modules.Users.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserResponseDTO {
    private String token;
    private Instant expires_in;
}
