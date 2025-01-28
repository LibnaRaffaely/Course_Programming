package com.rocketseat.courses_programming.modules.Users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestDTO {
    private String username;
    // criar forma de mudar o email dps por meio de uma validação do email anterior
    private String password;
    private String email;
}
