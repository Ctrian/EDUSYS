package com.uce.edusys.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.uce.edusys.repository.modelo.Personal;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.modelo.Rol;
import com.uce.edusys.repository.modelo.SolicitudMatricula;
import com.uce.edusys.service.IPersonalService;
import com.uce.edusys.service.ISolicitudMatriculaService;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private IPersonalService iPersonalService;

	@Autowired
	private ISolicitudMatriculaService iSolicitudMatriculaService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IRolRepository iRolRepository;

	// http://localhost:8080/personal/space
	@GetMapping("/space")
	public String vistaPersonal() {
		return "vistaPersonal";
	}

	// http://localhost:8080/personal/login
	// "Esto ya lo maneja MySimpleUrlAuthenticationSuccessHandler y
	// CustomAuthenticationEntryPoint para redirigir"

	// http://localhost:8080/personal/cuentaPer
	@GetMapping("/cuentaPer")
	public String cuentaPersonal(Model model, Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			String email = authentication.getName();
			try {
				Personal personal = iPersonalService.encontrarPorEmail(email);

				List<SolicitudMatricula> solicitudesPendientes = this.iSolicitudMatriculaService
						.encontrarPorEstado("Pendiente");
				model.addAttribute("solicitudesPendientes", solicitudesPendientes);

				model.addAttribute("personal", personal);
				return "vistaCuentaPersonal";
			} catch (Exception e) {
				// Manejo de excepciones específicas
				e.printStackTrace();
				return "redirect:/personal/login?error=notfound";
			}
		} else {
			return "redirect:/personal/login";
		}
	}

	// http://localhost:8080/personal/signUp
	@GetMapping("/signUp")
	public String showRegistrationForm(Personal personal) {
		return "vistaCrearCuentaPersonal";
	}

	// http://localhost:8080/personal/registrarP
	@PostMapping("/registrarP")
	public String registerPersonal(@ModelAttribute("personal") Personal personal, Model model) {
		String encodedPassword = passwordEncoder.encode(personal.getPassword());
		personal.setPassword(encodedPassword);
		Rol personalRole = iRolRepository.findByNombre("PERSONAL");
		if (personalRole != null) {
			Set<Rol> roles = new HashSet<>();
			roles.add(personalRole);
			personal.setRoles(roles);

			this.iPersonalService.registrarP(personal);

			model.addAttribute("nombre", personal.getNombre());
			return "redirect:/personal/login";
		}
		model.addAttribute("message", "Personal registrado exitosamente");
		return "vistaCrearCuentaPersonal";
	}

	@PostMapping("/aprobarSolicitud")
	public String aprobarSolicitud(@RequestParam("solicitudId") Integer solicitudId) {
		SolicitudMatricula solicitud = this.iSolicitudMatriculaService.encontrarSolicitud(solicitudId);
		solicitud.setEstado("Aprobada");
		this.iSolicitudMatriculaService.crearSolicitud(solicitud);

		Representante representante = solicitud.getRepresentante();
		Estudiante estudiante = solicitud.getEstudiante();

		emailService.enviarEmailSolicitudAceptada(representante, estudiante);
		return "vistaCuentaPersonal";
	}

	@PostMapping("/rechazarSolicitud")
	public String rechazarSolicitud(@RequestParam("solicitudId") Integer solicitudId) {
		SolicitudMatricula solicitud = this.iSolicitudMatriculaService.encontrarSolicitud(solicitudId);
		solicitud.setEstado("Rechazada");
		this.iSolicitudMatriculaService.crearSolicitud(solicitud);

		Representante representante = solicitud.getRepresentante();
		Estudiante estudiante = solicitud.getEstudiante();

		emailService.enviarEmailSolicitudRechazada(representante, estudiante);
		return "vistaCuentaPersonal";
	}
}
