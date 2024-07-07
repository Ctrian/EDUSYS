package com.uce.edusys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.repository.modelo.Personal;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.service.IPersonalService;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private IPersonalService iPersonalService;

    // http://localhost:8080/personal/space
	@GetMapping("/space")
	public String vistaPersonal() {
		return "vistaPersonal";
	}

    // http://localhost:8080/personal/login
	@GetMapping("/login")
	public String vistaLoginPersonal() {
		return "vistaIniciarSesionPersonal";
	}

	// http://localhost:8080/personal/cuentaPer
	@GetMapping("/cuentaPer")
	public String cuentaRepresentante(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			String email = authentication.getName();
			String pass = (String) authentication.getCredentials();
			try {
				Personal personal = iPersonalService.encontrarPorEmail(email);

				System.out.println("contrasenia: " + pass);

				model.addAttribute("representante", personal);
				return "vistaCuentaRepresentante";
			} catch (Exception e) {
				// Manejo de excepciones
				e.printStackTrace();
				return "redirect:/representantes/login?error=true";
			}
		} else {
			return "redirect:/representantes/login";
		}
	}

}
