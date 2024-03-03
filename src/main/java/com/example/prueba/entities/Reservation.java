package com.example.prueba.entities;

import com.example.prueba.entities.enums.LuggageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String departureDate;
    private String returnDate;
    private String departureTime;
    private String returnTime;
    private int seat;
    private String status;
    private float total;
    private LuggageType luggageType;

    @OneToOne
    @JoinColumn(name = "destination", referencedColumnName = "destination")
    private Destination destination;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<User> users;

    @OneToOne
    @JoinColumn(name = "plat", referencedColumnName = "plat")
    private Vehicle vehicle;
}
