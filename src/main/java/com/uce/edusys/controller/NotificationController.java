package com.uce.edusys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uce.edusys.configuracion.EmailRequest;
import com.uce.edusys.configuracion.EmailService;

@RestController
public class NotificationController {

    @Autowired
    private EmailService emailService;

    // Dirección de correo predefinida a la cual se enviarán todos los correos (LICEO CENTRAL)
    private final String predefinedEmail = "andrescalvache47@gmail.com";

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            emailService.sendSimpleMessage(request.getFromEmail(), predefinedEmail, request.getSubject(),
                    request.getBody());
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}
