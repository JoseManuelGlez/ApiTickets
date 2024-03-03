package com.example.prueba.controllers;

import com.example.prueba.controllers.dtos.requests.CreateUserRequest;
import com.example.prueba.controllers.dtos.requests.UserLoginRequest;
import com.example.prueba.controllers.dtos.responses.BaseResponse;
import com.example.prueba.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request){
        BaseResponse baseResponse = service.create(request);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody UserLoginRequest request){
        BaseResponse baseResponse = service.login(request);

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
