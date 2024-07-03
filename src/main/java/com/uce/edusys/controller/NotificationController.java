package com.uce.edusys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uce.edusys.configuracion.EmailService;
import com.uce.edusys.repository.modelo.EmailRequest;

@RestController
public class NotificationController {
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendSimpleMessage(request.getTo(), request.getSubject(), request.getText());
        return ResponseEntity.ok("Email sent successfully");
    }
}
