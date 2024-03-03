package com.example.prueba.services.interfaces;

import com.example.prueba.controllers.dtos.requests.CreateDestinationRequest;
import com.example.prueba.controllers.dtos.requests.GetDestinationRequest;
import com.example.prueba.controllers.dtos.responses.BaseResponse;
import com.example.prueba.entities.Destination;

import java.util.List;

public interface IDestinationService {
    BaseResponse create(CreateDestinationRequest request);

    BaseResponse list();

    Destination findDestinationByDestination(String destination);

    BaseResponse findDestinationsByStateAndCity(GetDestinationRequest request);
}
