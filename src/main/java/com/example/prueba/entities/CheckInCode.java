package com.example.prueba.entities;

import com.example.prueba.entities.enums.CheckInStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "check_in")
@Getter
@Setter
public class CheckInCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String code = String.valueOf(id);

    @Enumerated(EnumType.STRING)
    private CheckInStatus status;
}
