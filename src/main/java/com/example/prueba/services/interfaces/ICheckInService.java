package com.example.prueba.services.interfaces;

import com.example.prueba.controllers.dtos.requests.UpdateCheckInRequest;
import com.example.prueba.entities.CheckInCode;

public interface ICheckInService {
    CheckInCode create(String status);

    CheckInCode update(UpdateCheckInRequest request);
}
