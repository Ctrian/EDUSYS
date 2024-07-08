package com.uce.edusys.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edusys.configuracion.EmailService;
import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.repository.modelo.Estudiante;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.modelo.Rol;
import com.uce.edusys.service.IEstudianteService;
import com.uce.edusys.service.IRepresentanteService;

@Controller
@RequestMapping("/representantes")
public class RepresentanteController {

	@Autowired
	private IRolRepository iRolRepository;

	@Autowired
	private IRepresentanteService iRepresentanteService;

	@Autowired
	private IEstudianteService iEstudianteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

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

	// http://localhost:8080/representantes/matricular
	@GetMapping("/matricular")
	public String vistaMatricularEstudiante(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			String email = authentication.getName();
			try {
				Representante representante = iRepresentanteService.encontrarPorEmail(email);
				model.addAttribute("estudiante", new Estudiante());
				model.addAttribute("representanteId", representante.getId());
				return "vistaReprMatricular";
			} catch (Exception e) {
				// Manejo de excepciones
				e.printStackTrace();
				return "redirect:/representantes/cuentaR?error=true";
			}
		} else {
			return "redirect:/representantes/login";
		}
	}

	// http://localhost:8080/representantes/enviado
	@PostMapping("/enviado")
	public String vistaPeticiónMatricula(@ModelAttribute("estudiante") Estudiante estudiante,
			@RequestParam("representanteId") Integer representanteId,
			Model model) {

		Representante representante = this.iRepresentanteService.encontrarR(representanteId);

		if (representante != null) {
			model.addAttribute("nombre", representante.getNombre());

		String fromEmail = representante.getEmail(); // Dirección de correo del formulario (del representante)
		String toEmail = "andrescalvache47@gmail.com"; // Dirección de correo predefinida (LICEO CENTRAL)
		String subject = "Solicitud de matriculación";
		String body = "Se ha registrado una nueva solicitud de matricula con los siguientes datos:\n" +
				"Nombre del Representante: " + representante.getNombre() + "\n" +
				"Cédula del Representante: " + representante.getCedula() + "\n" +
				"Nombre del Estudiante: " + estudiante.getNombre() + "\n" +
				"Cédula del Estudiante: " + estudiante.getCedula();
		emailService.sendSimpleMessage(fromEmail, toEmail, subject, body);

		this.iEstudianteService.registrarE(estudiante);

		// SOLICITUD POR EL MOMENTO ACEPTADA (Integración del personal)

		//emailService.enviarEmailBienvenida(estudiante);
		emailService.enviarEmailBienvenidaR(representante, estudiante);

		//---------------

		

		model.addAttribute("mensaje", "Inscripción registrada exitosamente y correo enviado.");
		} else {
			model.addAttribute("mensaje", "Representante no encontrado. No se pudo registrar el estudiante.");
		}
		return "khe copy 2";
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
	public String listarRepresentantes(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			Model model) {
		@SuppressWarnings("unchecked")
		Page<Representante> representantes = (Page<Representante>) iRepresentanteService.encontrarTodos();
		model.addAttribute("representantes", representantes.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", representantes.getTotalPages());
		return "listarRepresentantes";
	}

	/*
	 * @GetMapping("/listar")
	 * public String listarRepresentantes(@RequestParam(name = "page", defaultValue
	 * = "0") int page,
	 * 
	 * @RequestParam(name = "size", defaultValue = "10") int size,
	 * Model model) {
	 * Page<Representante> representantes =
	 * iRepresentanteService.encontrarTodos(page, size);
	 * model.addAttribute("representantes", representantes.getContent());
	 * model.addAttribute("currentPage", page);
	 * model.addAttribute("totalPages", representantes.getTotalPages());
	 * return "listarRepresentantes";
	 * }
	 */

	@GetMapping("/buscarPorCedula")
	public String buscarPorCedula(@RequestParam("cedula") String cedula, Model model) {
		Representante representante = iRepresentanteService.encontrarPorCedula(cedula);
		model.addAttribute("representante", representante);
		return "vistaRepresentante";
	}

	@PostMapping("/logout")
	public String SalirRepresentante() {
		// La lógica de inicio de sesión será manejada por Spring Security
		return "redirect:/representantes/space";
	}

}
