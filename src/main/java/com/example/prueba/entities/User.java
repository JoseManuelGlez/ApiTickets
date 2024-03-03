package com.example.prueba.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String password;

    @ManyToOne
    private Reservation reservation;

    @OneToOne(mappedBy = "user")
    private DestinationReport destinationReport;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;
}
