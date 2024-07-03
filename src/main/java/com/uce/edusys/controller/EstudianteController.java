package com.uce.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.repository.modelo.Estudiante;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    // http://localhost:8080/estudiantes/space
	@GetMapping("/space")
	public String vistaPadres() {
		return "vistaEstudiantes";
	}

    // http://localhost:8080/estudiantes/login
	@GetMapping("/login")
	public String vistaRepresentantes() {
		return "vistaIniciarSesionEstudiante";
	}

	@PostMapping("/login")
    public String login() {
        // La lógica de inicio de sesión será manejada por Spring Security
        return "redirect:/estudiantes/cuenta";
    }

	// http://localhost:8080/estudiantes/cuenta
	@GetMapping("/cuenta")
	public String vistaCuentaPadres(@ModelAttribute("persona") Estudiante estudiante, Model model) {
		//Buscar el nombre mediante el ID de inicio de sesión
		// this.iRepresentanteService.encontrarR(null)
		model.addAttribute("nombre", estudiante.getNombre());
		return "vistaCuentaRepresentantes";
	}
}
