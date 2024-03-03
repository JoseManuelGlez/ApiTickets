package com.example.prueba.repositories;

import com.example.prueba.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDestinationRepository extends JpaRepository<Destination, Long> {
    Destination findDestinationByDestination(String destination);

    List<Destination> findDestinationsByStateAndCity(String state, String city);
}
