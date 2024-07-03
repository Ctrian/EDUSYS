package com.uce.edusys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.repository.modelo.Matricula;
import com.uce.edusys.repository.modelo.Representante;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {
    
    // http://localhost:8080/matriculas/formulario
	@GetMapping("/formulario")
	public String vistaPadres(Matricula matricula) {
		return "vistaInscripcion";
	}

	// enviar correos electronicos
	@PostMapping("/insertar")
	public String vistaPadresRegistro(@ModelAttribute("persona") Representante representante, Model model) {
	// String encodedPassword = passwordEncoder.encode(representante.getPassword());
	// representante.setPassword(encodedPassword);

	// this.iRepresentanteService.registrarR(representante);
	// model.addAttribute("nombre", representante.getNombre());
		return "redirect:/representantes/cuentaR";
	}
}
