package com.example.prueba.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetDestinationRequest {
    private String State;
    private String city;
}
