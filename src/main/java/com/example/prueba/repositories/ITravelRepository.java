package com.example.prueba.repositories;


import com.example.prueba.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravelRepository extends JpaRepository<Travel, Long> {
    Travel findIdById(Long travelId);
}
