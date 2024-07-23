package com.uce.edusys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.configuracion.EmailService;
import com.uce.edusys.repository.modelo.Matricula;
import com.uce.edusys.service.IMatriculaService;

@Controller
@RequestMapping("/menu")

public class MenuController {

	@Autowired
	private IMatriculaService iMatriculaService;

	@Autowired
	private EmailService emailService;

	// http://localhost:8080/menu/botones
	@GetMapping("/botones")
	public String vistaMenu() {
		return "home";
	}

	// http://localhost:8080/menu/contactar
	@GetMapping("/contactar")
	public String vistaRepresentantes() {
		return "vistaContacto";
	}

	/* <----------------- INFORMATION FORM -------------------> */

	// http://localhost:8080/menu/formulario
	@GetMapping("/formulario")
	public String vistaPadres(Matricula matricula) {
		return "vistaInscripcion";
	}

	// http://localhost:8080/menu/insertar
	@PostMapping("/insertar")
	public String vistaPadresRegistro(@ModelAttribute("matricula") Matricula matricula, Model model) {
		this.iMatriculaService.registrarM(null, null, null);
		model.addAttribute("nombre", matricula.getNombreRepresentante());

		String fromEmail = matricula.getEmailRepresentante(); // Dirección de correo del formulario
		String toEmail = "andrescalvache47@gmail.com"; // Dirección de correo predefinida (LICEO CENTRAL)
		String subject = "Nueva inscripción";
		String body = "Se ha registrado una nueva inscripción con los siguientes datos:\n" +
				"Nombre del Representante: " + matricula.getNombreRepresentante() + "\n" +
				"Cédula del Representante: " + matricula.getCedulaRepresentante() + "\n" +
				"Nombre del Estudiante: " + matricula.getNombreEstudiante();

		emailService.sendSimpleMessage(fromEmail, toEmail, subject, body);

		model.addAttribute("mensaje", "Inscripción registrada exitosamente y correo enviado.");
		return "khe copy";
	}
	
}
