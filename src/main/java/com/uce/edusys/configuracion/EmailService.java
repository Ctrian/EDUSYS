package com.uce.edusys.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.uce.edusys.repository.modelo.Estudiante;
import com.uce.edusys.repository.modelo.Representante;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String toEmail, String fromEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void enviarEmailBienvenidaR(Representante representante, Estudiante estudiante) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("andrescalvache47@gmail.com");
            message.setTo(representante.getEmail());
            message.setSubject("Aceptación de matrícula");
            message.setText(
                    "Felicitaciones a pasado la selección y entrevista personal \nBienvenido a Liceo Central de Ciencias y Arte \n"
                            +
                            "Reciba las credenciales para el ingreso a la plataforma de, " + estudiante.getNombre()
                            + "\n" +
                            "Usuario: " + estudiante.getEmail() + "\n" +
                            "Contraseña: " + estudiante.getPassword() + "\n" +
                            "Por su seguridad, le recomendamos encarecidamente que como primera acción cambie la contraseña de usuario a una propia");
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void enviarEmailBienvenida(Estudiante estudiante) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(estudiante.getEmail());
        message.setSubject("Bienvenido a la Institución");
        message.setText("Hola " + estudiante.getNombre() + ",\n\n" +
                "Te damos la bienvenida a nuestra institución. Tu usuario es: " + estudiante.getEmail()
                + " y tu contraseña es: " + estudiante.getPassword() + ".\n\n" +
                "Saludos,\nInstitución");

        javaMailSender.send(message);
    }

}
