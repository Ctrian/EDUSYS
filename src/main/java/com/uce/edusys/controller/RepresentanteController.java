package com.uce.edusys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.service.IRepresentanteService;

@Controller
@RequestMapping("/representantes")
public class RepresentanteController {

	@Autowired
	private IRepresentanteService iRepresentanteService;

	// http://localhost:8080/representantes/opciones
	@GetMapping("/opciones")
	public String vistaRepresentantes() {
		return "vistaLoginRepresentante";
	}

	// http://localhost:8080/representantes/crearCuenta
	@GetMapping("/crearCuenta")
	public String vistaRegistrarRepresentante(Representante representante) {
		return "vistaCrearCuentaRepresentante";
	}

	// http://localhost:8080/representantes/insertar
	@PostMapping("/insertar")
	public String insertarRepresentante(@ModelAttribute("persona") Representante representante, Model model) {
		this.iRepresentanteService.registrarR(representante);
		model.addAttribute("nombre", representante.getNombre());
		return "khe";
	}

	// http://localhost:8080/representantes/tyc
	@GetMapping("/tyc")
	public String vistaRepresentantesTyc() {
		return "terminosycondiciones";
	}

	// http://localhost:8080/representantes/fPassword
	@GetMapping("/fPassword")
	public String vistaRepresentantesfPassword() {
		return "vistaResetPassword";
	}

	// http://localhost:8080/representantes/space
	@GetMapping("/space")
	public String vistaCuentaRepresentante() {
		return "vistaCuentaRepresentante";
	}

	// http://localhost:8080/representantes/pagos
	@GetMapping("/pagos")
	public String vistaRepresentantePagos() {
		return "test";
	}

}
