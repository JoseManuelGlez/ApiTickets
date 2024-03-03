package com.example.prueba.services;

import com.example.prueba.controllers.dtos.requests.CreateDestinationRequest;
import com.example.prueba.controllers.dtos.requests.GetDestinationRequest;
import com.example.prueba.controllers.dtos.responses.BaseResponse;
import com.example.prueba.controllers.dtos.responses.CreateDestinationResponse;
import com.example.prueba.entities.Destination;
import com.example.prueba.entities.enums.DestinationStatus;
import com.example.prueba.repositories.IDestinationRepository;
import com.example.prueba.services.interfaces.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements IDestinationService {
    @Autowired
    private IDestinationRepository repository;

    @Override
    public BaseResponse create(CreateDestinationRequest request) {
        Destination user = from(request);

        return BaseResponse.builder()
                .data(from(repository.save(user)))
                .message("Destination created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse list() {
        List<CreateDestinationResponse> destinations = repository.findAll().stream().map(this::from).collect(Collectors.toList());

        return BaseResponse.builder()
                .data(destinations)
                .message("Destinations found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Destination findDestinationByDestination(String destination) {
        return repository.findDestinationByDestination(destination);
    }

    @Override
    public BaseResponse findDestinationsByStateAndCity(GetDestinationRequest request) {
        List<Destination> destinations = repository.findDestinationsByStateAndCity(request.getState(), request.getCity());

        List<Destination> availableDestinations = destinations.stream()
                .filter(destination -> destination.getDestinationStatus() == DestinationStatus.AVAILABLE)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(availableDestinations)
                .message("Destinations found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private Destination from(CreateDestinationRequest request){
        Destination destination = new Destination();

        destination.setDestination(request.getDestination());
        destination.setCity(request.getCity());
        destination.setState(request.getState());
        destination.setDestinationStatus(request.getDestinationStatus());

        return destination;
    }

    private CreateDestinationResponse from(Destination destination){
        CreateDestinationResponse response = new CreateDestinationResponse();

        response.setId(destination.getId());
        response.setDestination(destination.getDestination());
        response.setCity(destination.getCity());
        response.setState(response.getState());
        response.setDestinationStatus(response.getDestinationStatus());

        return response;

    }
}
