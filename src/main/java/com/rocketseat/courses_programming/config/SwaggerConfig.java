package com.rocketseat.courses_programming.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Cursos de Programação")
                        .description("API responsável por gerir aulas de cursos de programação").version("1.0"));

    }

    public SecurityScheme creaSecurityScheme() {
        return new SecurityScheme().name("jwt_auth").scheme("bearer").bearerFormat("JWT")
                .type(SecurityScheme.Type.HTTP);
    }
}
