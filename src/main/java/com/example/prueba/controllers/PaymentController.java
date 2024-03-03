package com.example.prueba.controllers;

import com.example.prueba.controllers.dtos.requests.CreatePaymentRequest;
import com.example.prueba.services.interfaces.IPaymentService;
import com.example.prueba.util.URLUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private IPaymentService service;

    public static final String PAYPAL_SUCCESS_URL = "pay/success";
    public static final String PAYPAL_CANCEL_URL = "pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(method = RequestMethod.POST, value = "pay")
    public String pay(@RequestBody CreatePaymentRequest request){
        String cancelUrl = URLUtils.getBaseURl((HttpServletRequest) request) + "/" + PAYPAL_CANCEL_URL;
        String successUrl = URLUtils.getBaseURl((HttpServletRequest) request) + "/" + PAYPAL_SUCCESS_URL;
        try {
            Payment payment = service.create(
                    request,
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
}
