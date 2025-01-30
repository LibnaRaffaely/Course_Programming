package com.rocketseat.courses_programming.modules.teachers.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String description;
}
