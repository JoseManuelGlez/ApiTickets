package com.example.prueba.services;

import com.example.prueba.controllers.dtos.requests.UpdateCheckInRequest;
import com.example.prueba.entities.CheckInCode;
import com.example.prueba.entities.enums.CheckInStatus;
import com.example.prueba.repositories.ICheckInRepository;
import com.example.prueba.services.interfaces.ICheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInImpl implements ICheckInService {
    @Autowired
    private ICheckInRepository repository;

    @Override
    public CheckInCode create(String status) {
        CheckInCode checkInCode = from(status);

        return repository.save(checkInCode);
    }

    @Override
    public CheckInCode update(UpdateCheckInRequest request) {
        CheckInCode code = repository.findCodeByCode(request.getCode());

        if (code != null){
            code.setStatus(CheckInStatus.valueOf("ASSIST"));
            return repository.save(code);
        }else{
            return null;
        }
    }

    private CheckInCode from(String status){
        CheckInCode checkInCode = new CheckInCode();

        checkInCode.setStatus(CheckInStatus.valueOf(status));

        return checkInCode;
    }
}
