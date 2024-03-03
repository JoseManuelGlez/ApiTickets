package com.example.prueba.controllers.dtos.responses;

import com.example.prueba.entities.enums.TravelType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class CreateTravelResponse {
    private UUID id;
    private String date;
    private String time;
    private TravelType type;
}
