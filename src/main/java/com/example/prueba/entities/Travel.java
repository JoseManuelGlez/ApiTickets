package com.example.prueba.entities;

import com.example.prueba.entities.enums.TravelType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "travels")
@Getter @Setter
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String date;
    private String time;

    @Enumerated(EnumType.STRING)
    private TravelType type;

    @OneToOne(mappedBy = "travel")
    private DestinationReport destinationReport;
}
