package com.uce.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.uce.edusys.repository.modelo.Estudiante;
import com.uce.edusys.service.IEstudianteService;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

	@Autowired
	private IEstudianteService iEstudianteService;

    // http://localhost:8080/estudiantes/space
	@GetMapping("/space")
	public String vistaPostulantes() {
		return "vistaEstudiantes";
	}

    // http://localhost:8080/estudiantes/login
	// @GetMapping("/login")
	// public String vistaEstudiantes() {
	// 	return "vistaIniciarSesionEstudiante";
	// }

	// http://localhost:8080/estudiantes/cuentaE
	@GetMapping("/cuentaE")
	public String vistaCuentaEstudiantes(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			String email = authentication.getName();
			String pass = (String) authentication.getCredentials();
			try {
				Estudiante estudiante = iEstudianteService.encontrarPorEmail(email);

				System.out.println("contrasenia: " + pass);

				model.addAttribute("estudiante", estudiante);
				return "vistaCuentaRepresentante";
			} catch (Exception e) {
				// Manejo de excepciones
				e.printStackTrace();
				return "redirect:/estudiantes/login?error=true";
			}
		} else {
			return "redirect:/estudiante/login";
		}
	}
}
