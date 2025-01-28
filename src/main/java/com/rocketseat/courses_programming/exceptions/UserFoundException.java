package com.rocketseat.courses_programming.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário com username e/ou email já existente no sistema");

    }
}
