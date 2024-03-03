package com.example.prueba.services;

import com.example.prueba.controllers.dtos.requests.CreateUserRequest;
import com.example.prueba.controllers.dtos.requests.UserLoginRequest;
import com.example.prueba.controllers.dtos.responses.BaseResponse;
import com.example.prueba.controllers.dtos.responses.CreateUserResponse;
import com.example.prueba.entities.User;
import com.example.prueba.repositories.IUserRepository;
import com.example.prueba.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = from(request);

        return BaseResponse.builder()
                .data(from(repository.save(user)))
                .message("User created corretly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public User findIdById(UUID userId) {
        return repository.findIdById(userId);
    }

    @Override
    public BaseResponse login(UserLoginRequest request) {
        User user = repository.findUserByEmailAndPassword(request.getEmail(), request.getPassword());

        return BaseResponse.builder()
                .data(from(repository.save(user)))
                .message("User found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public List<User> findUsers(List<User> users) {
        return repository.findUsersByUserList(users);
    }

    @Override
    public User findIdByEmail(String email) {
        return repository.findIdByEmail(email);
    }

    private User from(CreateUserRequest request){
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }

    private CreateUserResponse from(User user){
        CreateUserResponse response = new CreateUserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());

        return response;
    }
}
