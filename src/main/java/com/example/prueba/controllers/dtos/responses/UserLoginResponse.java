package com.example.prueba.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class UserLoginResponse {
    private UUID id;
    private String email;
    private String password;
}
