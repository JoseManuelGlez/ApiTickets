package com.example.prueba.services;

import com.example.prueba.controllers.dtos.requests.CreateTravelRequest;
import com.example.prueba.controllers.dtos.responses.BaseResponse;
import com.example.prueba.controllers.dtos.responses.CreateTravelResponse;
import com.example.prueba.entities.Travel;
import com.example.prueba.repositories.ITravelRepository;
import com.example.prueba.services.interfaces.ITravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TravelServiceImpl implements ITravelService {
    @Autowired
    private ITravelRepository repository;

    @Override
    public BaseResponse create(CreateTravelRequest request) {
        Travel travel = from(request);

        return BaseResponse.builder()
                .data(from(repository.save(travel)))
                .message("Travel created corretly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public Travel findIdById(UUID travelId) {
        return repository.findIdById(travelId);
    }

    private Travel from(CreateTravelRequest request){
        Travel travel = new Travel();

        travel.setDate(request.getDate());
        travel.setTime(request.getTime());
        travel.setType(request.getType());

        return travel;
    }

    private CreateTravelResponse from(Travel travel){
        CreateTravelResponse response = new CreateTravelResponse();

        response.setId(travel.getId());
        response.setDate(travel.getDate());
        response.setTime(travel.getTime());
        response.setType(travel.getType());

        return response;
    }
}
