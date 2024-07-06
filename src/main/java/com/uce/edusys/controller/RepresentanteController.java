package com.uce.edusys.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.paginacion.PageRender;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.modelo.Rol;
import com.uce.edusys.service.IRepresentanteService;

@Controller
@RequestMapping("/representantes")
public class RepresentanteController {

	@Autowired
	private IRolRepository iRolRepository;

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
	@GetMapping("/cuentaR")
	public String cuentaRepresentante(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			String email = authentication.getName();
			String pass = (String) authentication.getCredentials();
			try {
				Representante representante = iRepresentanteService.encontrarPorEmail(email);

				System.out.println("contrasenia: " + pass);

				model.addAttribute("representante", representante);
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

	// http://localhost:8080/representantes/signUp
	@GetMapping("/signUp")
	public String vistaRegistrarRepresentante(Representante representante) {
		return "vistaCrearCuentaRepresentante";
	}

	// http://localhost:8080/representantes/insertar
	@PostMapping("/insertar")
	public String insertarRepresentante(@ModelAttribute("representante") Representante representante, Model model) {
		String encodedPassword = passwordEncoder.encode(representante.getPassword());
		representante.setPassword(encodedPassword);
		Rol representanteRole = iRolRepository.findByNombre("REPRESENTANTE");
		if (representanteRole != null) {
			Set<Rol> roles = new HashSet<>();
			roles.add(representanteRole);
			representante.setRoles(roles);

			this.iRepresentanteService.registrarR(representante);

			model.addAttribute("nombre", representante.getNombre());
			return "khe"; // Aquí coloca la vista a la que deseas redirigir después del registro
		} else {
			model.addAttribute("error", "Error al asignar rol REPRESENTANTE");
			return "vistaCrearCuentaRepresentante";
		}
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

}
