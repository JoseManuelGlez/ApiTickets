package com.example.prueba.repositories;

import com.example.prueba.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findIdById(UUID userId);

    User findUserByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u IN :users")
    List<User> findUsersByUserList(@Param("users") List<User> users);

    User findIdByEmail(String email);
}
