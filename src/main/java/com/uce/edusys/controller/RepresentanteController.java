package com.uce.edusys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.uce.edusys.paginacion.PageRender;
import com.uce.edusys.repository.IRepresentanteRepository;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.service.IRepresentanteService;

@Controller
@RequestMapping("/representantes")
public class RepresentanteController {

	@Autowired
	private IRepresentanteRepository iRepresentanteRepository;

	@Autowired
	private IRepresentanteService iRepresentanteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// http://localhost:8080/representantes/space
	@GetMapping("/space")
	public String vistaPadres() {
		return "vistaPadres";
	}

	// http://localhost:8080/representantes/login
	@GetMapping("/login")
	public String vistaRepresentantes() {
		return "vistaIniciarSesionRepresentante";
	}

	// http://localhost:8080/representantes/cuentaR
	@PostMapping("/cuentaR")
	public String login(@ModelAttribute("persona") Representante representante,
			Model model) {
		model.addAttribute("nombre", representante.getNombre());
		return "vistaCuentaRepresentante";
	}

	// http://localhost:8080/representantes/signUp
	@GetMapping("/signUp")
	public String vistaRegistrarRepresentante(Representante representante) {
		return "vistaCrearCuentaRepresentante";
	}

	// http://localhost:8080/representantes/insertar
	@PostMapping("/insertar")
	public String insertarRepresentante(@ModelAttribute("persona") Representante representante, Model model) {
		String encodedPassword = passwordEncoder.encode(representante.getPassword());
		representante.setPassword(encodedPassword);

		this.iRepresentanteService.registrarR(representante);
		model.addAttribute("nombre", representante.getNombre());
		return "khe";
	}

	// @PostMapping("/insertar")
	// public String insertarRepresentante(@Valid @ModelAttribute("persona")
	// Representante representante, BindingResult bindingResult, Model model) {
	// if (bindingResult.hasErrors()) {
	// // Si hay errores de validación, volver al formulario con los errores
	// return "vistaCrearCuentaRepresentante"; // Nombre de la vista Thymeleaf
	// }

	// // Lógica para guardar el representante si la validación es exitosa
	// this.iRepresentanteService.registrarR(representante);
	// model.addAttribute("nombre", representante.getNombre());
	// return "khe"; // Página de éxito
	// }

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

	@PostMapping("/logout")
	public String SalirRepresentante() {
		// La lógica de inicio de sesión será manejada por Spring Security
		return "redirect:/representantes/space";
	}

	// http://localhost:8080/representantes/pagos
	@GetMapping("/pagos")
	public String vistaRepresentantePagos() {
		return "test";
	}

	// http://localhost:8080/representantes/listar
	@GetMapping("listar")
	public String listarEmpleados(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Representante> representantes = this.iRepresentanteService.encontrarTodos(pageRequest);
		PageRender<Representante> pageRender = new PageRender<>("/listar", representantes);

		model.addAttribute("titulo", "Listado de representantes");
		model.addAttribute("representantes", representantes);
		model.addAttribute("page", pageRender);

		return "listar";
	}

	// http://localhost:8080/representantes/session
	// @GetMapping("/session")
	// public ResponseEntity<?> getDetailsSession() {

	// String sessionld = "";
	// User user0bject = null;
	// List<Object> sessions = SessionRegistry.getAllPrincipals();

	// for (Object session : sessions) {
	// if(session instanceof User) {
	// userObject = (User) session;
	// }

	// List<SessionInformation> SessionInformations =
	// sessionRegistry.getAllSesions(session, false);

	// for (SessionInformation : SessionInformations) {
	// sessionId = SessionInformation.getSessionId();
	// }
	// }

	// Map<String, Object> response = new HashMap<> ();
	// response.put("response", "Hello world");
	// response.put("sessionId", sessionId);
	// response.put("sessionUser", userObject);

	// return ResponseEntity.ok(response);
	// }

}
