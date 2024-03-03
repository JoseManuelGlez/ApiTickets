package com.example.prueba.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class GetDestinationResponse {
    private UUID id;
    private String State;
    private String city;
}
