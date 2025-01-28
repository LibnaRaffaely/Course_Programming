package com.rocketseat.courses_programming.modules.Users.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
}
