package com.uce.edusys.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.uce.edusys.configuracion.EmailService;
import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.configuracion.seguridad.TokenService;
import com.uce.edusys.repository.modelo.Curso;
import com.uce.edusys.repository.modelo.Estudiante;
import com.uce.edusys.repository.modelo.Factura;
import com.uce.edusys.repository.modelo.Matricula;
import com.uce.edusys.repository.modelo.OfertaAcademica;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.modelo.Rol;
import com.uce.edusys.repository.modelo.SolicitudMatricula;
import com.uce.edusys.service.ICursoService;
import com.uce.edusys.service.IEstudianteService;
import com.uce.edusys.service.IFacturaService;
import com.uce.edusys.service.IMatriculaService;
import com.uce.edusys.service.IOfertaAcademicaService;
import com.uce.edusys.service.IRepresentanteService;
import com.uce.edusys.service.ISolicitudMatriculaService;

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

	@Autowired
	private TokenService tokenService;

	@Autowired
	private ISolicitudMatriculaService iSolicitudMatriculaService;

	@Autowired
	private IOfertaAcademicaService iOfertaAcademicaService;

	@Autowired
	private IFacturaService iFacturaService;

	@Autowired
	private IMatriculaService iMatriculaService;

	// http://localhost:8080/representantes/space
	@GetMapping("/space")
	public String vistaPadres() {
		return "vistaPadres";
	}

	// http://localhost:8080/representantes/login
	// "Esto ya lo maneja MySimpleUrlAuthenticationSuccessHandler y
	// CustomAuthenticationEntryPoint para redirigir"

	// http://localhost:8080/representantes/cuentaR
	@GetMapping("/cuentaR")
	public String cuentaRepresentante(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			String email = authentication.getName();
			try {
				Representante representante = iRepresentanteService.encontrarPorEmail(email);

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
	// Crear representante
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
			return "khe";
		} else {
			model.addAttribute("error", "Error al asignar rol REPRESENTANTE");
			return "vistaCrearCuentaRepresentante";
		}
	}

	// http://localhost:8080/representantes/registrarE
	@GetMapping("/registrarE")
	public String registrarEstudiante(Authentication authentication, Model model) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			String email = authentication.getName();
			try {
				Representante representante = iRepresentanteService.encontrarPorEmail(email);
				model.addAttribute("estudiante", new Estudiante());
				model.addAttribute("representanteId", representante.getId());
				model.addAttribute("cedulaRepresentante", representante.getCedula());
				return "vistaReprRegistrarE";
			} catch (Exception e) {
				// Manejo de excepciones
				e.printStackTrace();
				return "redirect:/representantes/cuentaR?error=true";
			}
		} else {
			return "redirect:/representantes/cuentaR";
		}
	}

	@GetMapping("/validarCedula")
	@ResponseBody
	public ResponseEntity<Boolean> validarCedula(@RequestParam("cedula") String cedula) {
		boolean exists = iEstudianteService.existsByCedula(cedula);
		return ResponseEntity.ok(exists);
	}

	// http://localhost:8080/representantes/estadoRegistro
	@PostMapping("/estadoRegistro")
	public String peticionRegistro(@ModelAttribute("estudiante") Estudiante estudiante,
			@RequestParam("representanteId") Integer representanteId,
			Model model) {

		Representante representante = this.iRepresentanteService.encontrarR(representanteId);

		if (representante != null) {
			model.addAttribute("nombreE", estudiante.getNombre());

			// comprobación de si la cédula existe en la Base
			if (this.iEstudianteService.existsByCedula(estudiante.getCedula())) {
				return "vistaReprRegistrarE"; // Redirige al formulario para corregir
			}

			// "Asignar el id de representante al estudiante"
			estudiante.setRepresentante(representante);

			// "Asignar el rol "
			Rol studentRole = iRolRepository.findByNombre("ESTUDIANTE");
			if (studentRole != null) {
				estudiante.getRoles().add(studentRole);
			}

			this.iEstudianteService.registrarE(estudiante);

			emailService.registroSolicitudMatriculas(representante, estudiante);

			// Crear una nueva solicitud de matrícula
			SolicitudMatricula solicitud = new SolicitudMatricula();
			solicitud.setRepresentante(representante);
			solicitud.setEstudiante(estudiante);
			solicitud.setFecha(LocalDate.now());
			solicitud.setEstado("Pendiente");
			this.iSolicitudMatriculaService.crearSolicitud(solicitud);

			emailService.enviarInvitacion(representante, estudiante);

			model.addAttribute("mensaje", "Registro exitoso");
			return "khe copy 2";
		} else {
			model.addAttribute("mensaje", "Representante no encontrado. No se pudo registrar el estudiante.");
		}
		return "vistaReprRegistrarE";
	}

	@GetMapping("/matricular")
	public String verOfertasAcademicas(Authentication authentication, Model model) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			try {
				String email = authentication.getName();
				Representante representante = this.iRepresentanteService.encontrarPorEmail(email);

				// Obtener todas las ofertas académicas
				List<OfertaAcademica> ofertasAcademicas = this.iOfertaAcademicaService.encontrarTodos();

				// Clasificar las ofertas por hora del día
				List<OfertaAcademica> ofertasDia = ofertasAcademicas.stream()
						.filter(oferta -> oferta.getHora().equals("07:00 - 13:00"))
						.collect(Collectors.toList());

				List<OfertaAcademica> ofertasTarde = ofertasAcademicas.stream()
						.filter(oferta -> oferta.getHora().equals("13:00 - 18:00"))
						.collect(Collectors.toList());

				List<Estudiante> estudiantes = representante.getEstudiantes();

				model.addAttribute("estudiantes", estudiantes);
				model.addAttribute("ofertasDia", ofertasDia);
				model.addAttribute("ofertasTarde", ofertasTarde);
				model.addAttribute("representanteId", representante.getId());

				return "vistaReprMatricular";
			} catch (Exception e) {
				// Manejo de excepciones
				e.printStackTrace();
				return "redirect:/representantes/cuentaR?error=true";
			}
		} else {
			return "redirect:/representantes/cuentaR";
		}
	}

	@PostMapping("/enviado")
	public String matricularEstudiante(@RequestParam("offer") Integer ofertaAcademicaId,
			@RequestParam("estudianteId") Integer estudianteId,
			Authentication authentication,
			Model model) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			try {
				String email = authentication.getName();
				Representante representante = this.iRepresentanteService.encontrarPorEmail(email);

				this.iMatriculaService.registrarM(ofertaAcademicaId, estudianteId, representante.getId());

				model.addAttribute("mensaje", "Matriculación exitosa y correo enviado.");
				return "redirect:/representantes/cuentaR?success=true";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/representantes/cuentaR?error=true";
			}
		} else {
			return "redirect:/representantes/cuentaR";
		}
	}

	// http://localhost:8080/representantes/tyc
	@GetMapping("/tyc")
	public String vistaRepresentantesTyc() {
		return "terminosycondiciones";
	}

	// http://localhost:8080/representantes/reset-password
	@GetMapping("/reset-password")
	public String vistaRepresentantesfPassword(Model model) {
		model.addAttribute("email", new String());
		return "olvideMiContraseniaRepres";
	}

	// http://localhost:8080/representantes/reset-request
	@PostMapping("/reset-request")
	public String handleResetPasswordRequest(@RequestParam("email") String email, Model model) {
		Representante representante = iRepresentanteService.encontrarPorEmail(email);
		if (representante == null) {
			model.addAttribute("message", "No se encontró una cuenta con ese correo electrónico.");
			return "olvideMiContraseniaRepres";
		}

		// Generar token de confirmación
		String token = tokenService.createToken(representante);
		String resetLink = "http://localhost:8080/representantes/reset-password/confirm?token=" + token;

		// Enviar correo de confirmación
		String subject = "Solicitud de cambio de contraseña";
		String body = "Estimado/a [individuo] \n¿Ha olvidado la contraseña? \n Este vínculo caducará dentro de 30 minutos y solo se podrá usar una vez --> "
				+ resetLink + "\n"
				+ " Si no desea cambiar la contraseña o no lo ha solicitado, ignore este mensaje y bórrelo." + "\n" +
				"\nGracias, \nEquipo de cuentas de Liceo Central de Ciencias y Arte";
		emailService.sendSimpleMessage("andrescalvache47@gmail.com", email, subject, body);

		model.addAttribute("message", "Se ha enviado un enlace de cambio de contraseña a su correo electrónico.");
		return "olvideMiContraseniaRepres";
	}

	// http://localhost:8080/representantes/reset-password/confirm
	@GetMapping("/reset-password/confirm")
	public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
		model.addAttribute("token", token);
		return "resetContraseniaRepres";
	}

	// http://localhost:8080/representantes/reset-password/reset
	@PostMapping("/reset-password/reset")
	public String handleResetPassword(@RequestParam("token") String token,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword,
			Model model) {
		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("message", "Las contraseñas no coinciden.");
			model.addAttribute("token", token);
			return "resetContraseniaRepres";
		}

		String email = tokenService.validateToken(token);
		if (email == null) {
			model.addAttribute("message", "Enlace de cambio de contraseña inválido o expirado.");
			return "resetContraseniaRepres";
		}

		Representante representante = iRepresentanteService.encontrarPorEmail(email);
		representante.setPassword(passwordEncoder.encode(newPassword));
		iRepresentanteService.actualizarR(representante);

		model.addAttribute("message", "Contraseña cambiada exitosamente.");
		return "vistaIniciarSesionRepresentante";
	}

	// http://localhost:8080/representantes/pagos
	@GetMapping("/pagos")
	public String vistaRepresentantePagos() {
		return "test";
	}

	// http://localhost:8080/representantes/pagos/factura
	@GetMapping("/pagos/factura")
	public String mostrarFactura(Model model, Authentication authentication) {
	    if (authentication != null && authentication.isAuthenticated()
	            && !(authentication instanceof AnonymousAuthenticationToken)) {
	        String email = authentication.getName();
	        try {
	            Representante representante = iRepresentanteService.encontrarPorEmail(email);
	            // Suponiendo que tienes un método para obtener la factura, por ejemplo:
	            Factura factura = iFacturaService.obtenerFacturaPorRepresentante(representante.getId());

	            model.addAttribute("representante", representante);
	            model.addAttribute("factura", factura);

	            return "vistaFactura";
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "redirect:/representantes/login?error=true";
	        }
	    } else {
	        return "redirect:/representantes/login";
	    }
	}

	// http://localhost:8080/representantes/pagos/pendiente
    @GetMapping("/pagos/pendiente")
    public String mostrarFacturaPendiente(Model model, Authentication authentication) {
        // Lógica para mostrar la factura pendiente
        return "vistaFacturaPendiente";
    }
    //http://localhost:8080/representantes/pagos/exitoso
    @GetMapping("/pagos/exitoso")
    public String mostrarFacturaPagoExitoso(Model model, Authentication authentication) {
        // Lógica para mostrar la factura de pago exitoso
        return "pago-exito";
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
	 * 
	 * @GetMapping("/buscarPorCedula")
	 * public String buscarPorCedula(@RequestParam("cedula") String cedula, Model
	 * model) {
	 * Representante representante =
	 * iRepresentanteService.encontrarPorCedula(cedula);
	 * model.addAttribute("representante", representante);
	 * return "vistaRepresentante";
	 * }
	 * 
	 * @PostMapping("/logout")
	 * public String SalirRepresentante() {
	 * // La lógica de inicio de sesión será manejada por Spring Security
	 * return "redirect:/representantes/space";
	 * }
	 */

}
