package com.example.prueba.repositories;

import com.example.prueba.entities.CheckInCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICheckInRepository extends JpaRepository<CheckInCode, UUID> {
    CheckInCode findCodeByCode(String code);
}
