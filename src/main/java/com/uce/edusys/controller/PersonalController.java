package com.uce.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.repository.modelo.Personal;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    // http://localhost:8080/personal/space
	@GetMapping("/space")
	public String vistaPadres() {
		return "vistaPersonal";
	}

    // http://localhost:8080/personal/login
	@GetMapping("/login")
	public String vistaRepresentantes() {
		return "vistaIniciarSesionPersonal";
	}

	@PostMapping("/login")
    public String login() {
        // La lógica de inicio de sesión será manejada por Spring Security
        return "redirect:/personal/cuenta";
    }

	// http://localhost:8080/personal/cuenta
	@GetMapping("/cuenta")
	public String vistaCuentaPadres(@ModelAttribute("persona") Personal personal, Model model) {
		//Buscar el nombre mediante el ID de inicio de sesión
		// this.iRepresentanteService.encontrarR(null)
		model.addAttribute("nombre", personal.getNombre());
		return "vistaCuentaRepresentantes";
	}

}
