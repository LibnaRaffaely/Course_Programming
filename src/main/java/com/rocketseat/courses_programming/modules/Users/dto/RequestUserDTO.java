package com.rocketseat.courses_programming.modules.Users.dto;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RequestUserDTO {
    @Schema(example = "Carlos Lima Costa")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço!")
    @Schema(example = "CarlosLima")
    private String username;

    @NotBlank
    @Length(min = 10, max = 100)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "A senha deve conter pelo menos:\n1 numero\n 1 letra Maiúscula e minuscula \n1 Caractere especial \nSem espaço em branco")
    @Schema(example = "986L6@CoIpH11")
    private String password;

    @Email(message = "O email deve ser válido!")
    @Schema(example = "CarlosL@gmail.com")
    private String email;
}
