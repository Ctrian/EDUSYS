package com.uce.edusys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.repository.modelo.Matricula;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.service.IMatriculaService;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

	@Autowired
	private IMatriculaService iMatriculaService;

	// http://localhost:8080/matriculas/formulario
	@GetMapping("/formulario")
	public String vistaPadres(Matricula matricula) {
		return "vistaInscripcion";
	}

	// enviar correos electronicos
	@PostMapping("/insertar")
	public String vistaPadresRegistro(@ModelAttribute("matricula") Matricula matricula, Model model) {

		// this.iRepresentanteService.registrarR(representante);
		// model.addAttribute("nombre", representante.getNombre());
		this.iMatriculaService.registrarM(matricula);
		model.addAttribute("nombre", matricula.getNombreRepresentante());
		return "khe copy";
	}
}
