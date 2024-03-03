package com.example.prueba.services.interfaces;

import com.example.prueba.controllers.dtos.requests.CreatePaymentRequest;
import com.example.prueba.controllers.dtos.responses.BaseResponse;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface IPaymentService {
    Payment create(CreatePaymentRequest request, String cancelUrl, String successUrl) throws PayPalRESTException;
}
