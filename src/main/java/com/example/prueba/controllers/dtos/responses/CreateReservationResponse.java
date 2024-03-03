package com.example.prueba.controllers.dtos.responses;

import com.example.prueba.entities.User;
import com.example.prueba.entities.enums.LuggageType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class CreateReservationResponse {
    private UUID id;
    private String departureDate;
    private String returnDate;
    private String departureTime;
    private String returnTime;
    private int seat;
    private String status;
    private float total;
    private LuggageType luggageType;

    private DestinationResponse destination;
    private List<User> users;
    private PlatVehicleResponse plat;
}
