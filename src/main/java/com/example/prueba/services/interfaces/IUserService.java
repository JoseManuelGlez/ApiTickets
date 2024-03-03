package com.example.prueba.services.interfaces;

import com.example.prueba.controllers.dtos.requests.CreateUserRequest;
import com.example.prueba.controllers.dtos.requests.UserLoginRequest;
import com.example.prueba.controllers.dtos.responses.BaseResponse;
import com.example.prueba.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    BaseResponse create(CreateUserRequest request);

    User findIdById(UUID userId);

    BaseResponse login(UserLoginRequest request);

    List<User> findUsers(List<User> users);

    User findIdByEmail(String email);
}
