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

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private static final String emailLiceo = "andrescalvache47@gmail.com";
    private static final String linkMatricula = "http://localhost:8080/representantes/matricular";
    private static final String linkPagos = "http://localhost:8080/representantes/pagos";
    private static final String linkHome = "http://localhost:8080/menu/botones";

    public void sendSimpleMessage(String fromEmail, String toEmail, String subject, String body) {
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

    public void registroSolicitudMatriculas(Representante representante, Estudiante estudiante) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(representante.getEmail());
            message.setTo(emailLiceo);
            message.setSubject("Solicitud de matriculación");
            message.setText("Se ha registrado una nueva solicitud de matrícula con los siguientes datos:\n" +
                    "Nombre del Representante: " + representante.getNombre() + "\n" +
                    "Cédula del Representante: " + representante.getCedula() + "\n" +
                    "Nombre del Estudiante: " + estudiante.getNombre() + "\n" +
                    "Cédula del Estudiante: " + estudiante.getCedula());
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void enviarInvitacion(Representante representante, Estudiante estudiante) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailLiceo);
            message.setTo(representante.getEmail());
            message.setSubject("Entrevista Personal");
            message.setText("Hola, " + representante.getNombre() + "\n" + "Has iniciado el proceso de matriculación de "
                    + estudiante.getNombre() + "\n"
                    + "Para avanzar al segundo paso MATRICULA necesitan aprobar la Entrevista Personal y una evaluación presencial\n"
                    + "\n"
                    + "Estan cordialmente invitados a nuestras instalaciones en Calle Imaginaria 123, Quito - Ecuador\n"
                    + "Entrada principal Norte\n" + "Dia - Lunes 00-00-0000\n" + "Hora - 07:00am" + "\n" +
                    "Saludos,\nEquipo de cuentas de Liceo Central de Ciencias y Arte");
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void enviarEmailSolicitudAceptada(Representante representante, Estudiante estudiante) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailLiceo);
            message.setTo(representante.getEmail());
            message.setSubject("Solicitud de matrícula Aceptada");
            message.setText(
                    "Felicitaciones a pasado la selección y entrevista personal \nEl siguiente paso es MATRICULAR\n" +
                            "\n" +
                            "Te recomendamos ver nuestras ofertas académicas aquí -> " + linkHome + " \n" +
                            "Puedes dirigirte al proceso MATRICULA directamente desde aquí -> " + linkMatricula + " \n"
                            +
                            "Saludos,\nEquipo de cuentas de Liceo Central de Ciencias y Arte");
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void enviarEmailBienvenidaE(Estudiante estudiante) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailLiceo);
        message.setTo(estudiante.getEmail());
        message.setSubject("Bienvenido a la Institución");
        message.setText("Hola " + estudiante.getNombre() + ",\n\n" +
                "Te damos la bienvenida a nuestra institución\n"
                + "Estas son tus credenciales para acceder a nuestros servicios\nTu usuario es: "
                + estudiante.getEmail()
                + " y tu contraseña es: " + estudiante.getPassword() + "\n"
                + "Te recomendamos encarecidamente que cambies la contraseña por una propia" + "\n" +
                "Saludos,\nEquipo de cuentas de Liceo Central de Ciencias y Arte");

        javaMailSender.send(message);
    }

    public void enviarEmailSolicitudRechazada(Representante representante, Estudiante estudiante) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailLiceo);
            message.setTo(representante.getEmail());
            message.setSubject("Solicitud de matrícula rechazada");
            message.setText(
                    "Lo lamentamos mucho pero no ha pasado la selección y entrevista personal\n" +
                            "No responda a este mensaje, gracias \n" + "\n" +
                            "Saludos,\nEquipo de cuentas de Liceo Central de Ciencias y Arte");
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void enviarEmailCredenciales(Representante representante, Estudiante estudiante) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailLiceo);
            message.setTo(representante.getEmail());
            message.setSubject("Matrícula Registrada");
            message.setText(
                    "Felicitaciones a pasado la selección y entrevista personal \nLes damos la bienvenida a Liceo Central de Ciencias y Arte \n"
                            +
                            "Reciba las credenciales para el ingreso a la plataforma de, " + estudiante.getNombre()
                            + "\n" +
                            "Usuario: " + estudiante.getEmail() + "\n" +
                            "Contraseña: " + estudiante.getPassword() + "\n" +
                            "Por su seguridad, le recomendamos encarecidamente que como primera acción cambie la contraseña de usuario a una propia"
                            + "\n" +
                            "Ahora puedes dirigirte al proceso PAGOS aqui -> " + linkPagos + " \n" +
                            "Saludos,\nEquipo de cuentas de Liceo Central de Ciencias y Arte");
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
