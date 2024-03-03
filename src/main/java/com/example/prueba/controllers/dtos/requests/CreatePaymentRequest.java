package com.example.prueba.controllers.dtos.requests;

import com.example.prueba.paypal.PaypalPaymentIntent;
import com.example.prueba.paypal.PaypalPaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class CreatePaymentRequest {
    private String currency;
    private Double Total;
    private String Description;
    private PaypalPaymentMethod method;
    private PaypalPaymentIntent intent;
    private String email;
    private UUID userId;
}
