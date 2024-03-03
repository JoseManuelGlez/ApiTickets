package com.example.prueba.services;

import com.example.prueba.controllers.dtos.requests.CreatePaymentRequest;
import com.example.prueba.entities.CheckInCode;
import com.example.prueba.entities.DestinationReport;
import com.example.prueba.entities.User;
import com.example.prueba.services.interfaces.ICheckInService;
import com.example.prueba.services.interfaces.IDestinationReportService;
import com.example.prueba.services.interfaces.IPaymentService;
import com.example.prueba.services.interfaces.IUserService;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private APIContext apiContext;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private IUserService userService;

    @Autowired
    private IDestinationReportService destinationReportService;

    @Autowired
    private ICheckInService checkInService;

    @Override
    public Payment create(CreatePaymentRequest request, String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(request.getCurrency());
        Double total = new BigDecimal(request.getTotal()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(request.getMethod().toString());

        Payment payment = new Payment();
        payment.setIntent(request.getIntent().toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        User user = userService.findIdByEmail(request.getEmail());
        DestinationReport destinationReport = destinationReportService.findDestinationReportByUserId(user);
        CheckInCode checkInCode = checkInService.create("NO_ASSIST");

        sendPaymentConfirmationEmail(payment, request.getEmail(), user, destinationReport, checkInCode);

        return payment.create(apiContext);
    }

    private void sendPaymentConfirmationEmail(Payment payment, String userEmail, User user, DestinationReport destinationReport, CheckInCode checkInCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(userEmail);
        message.setSubject("Confirmación de pago");
        message.setText("Se ha realizado un pago por el monto de " + payment.getTransactions().get(0).getAmount().getTotal() + " " + payment.getTransactions().get(0).getAmount().getCurrency() + "Usuario: " + user + " " + "Destino: " + destinationReport + " " + "Codigo: " + checkInCode.getCode());

        javaMailSender.send(message);
    }
}