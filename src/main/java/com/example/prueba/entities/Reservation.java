package com.example.prueba.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String date;
    private String time;
    private int seat;
    private String status;
    private float total;

    @OneToOne
    @JoinColumn(name = "destination", referencedColumnName = "destination")
    private Destination destination;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "plat", referencedColumnName = "plat")
    private Vehicle vehicle;
}
