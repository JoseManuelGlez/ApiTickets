package com.example.prueba.repositories;

import com.example.prueba.entities.DestinationReport;
import com.example.prueba.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDestinationReportRepository extends JpaRepository<DestinationReport, Long> {
    DestinationReport findDestinationReportByUserId(User user);
}
