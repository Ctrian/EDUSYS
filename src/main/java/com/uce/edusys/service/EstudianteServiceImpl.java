package com.uce.edusys.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.configuracion.EmailService;
import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.repository.IEstudianteRepository;
import com.uce.edusys.repository.modelo.Estudiante;
import com.uce.edusys.repository.modelo.Rol;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final IEstudianteRepository iEstudianteRepository;
    private final IRolRepository iRolRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public EstudianteServiceImpl(IEstudianteRepository estudianteRepository, IRolRepository iRolRepository,
            PasswordEncoder passwordEncoder, EmailService emailService) {
        this.iEstudianteRepository = estudianteRepository;
        this.iRolRepository = iRolRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public void registrarE(Estudiante estudiante) {
        Rol role = iRolRepository.findByNombre("ROLE_ESTUDIANTE");
        // representante.getRoles().add(role);
        if (!estudiante.getRoles().contains(role)) {
            estudiante.getRoles().add(role);
        }

        // Generar email y contraseña
        String email = generateStudentEmail(estudiante.getNombre(), estudiante.getApellido());
        String password = generateRandomPassword();

        estudiante.setEmail(email);
        estudiante.setPassword(passwordEncoder.encode(password));

        this.iEstudianteRepository.save(estudiante);

        // Aquí puedes enviar el correo con las credenciales al estudiante y al
        // representante

        // Enviar correos electrónicos
        /*
         * String mensaje = String.
         * format("Bienvenido %s %s! Sus credenciales son: Email: %s, Contraseña: %s",
         * estudiante.getNombre(), estudiante.getApellido(), email, password);
         * emailService.sendSimpleMessage(email, "Credenciales de acceso", mensaje);
         * emailService.enviarCorreo(estudiante.getRepresentante().getEmail(),
         * "Registro de estudiante", mensaje);
         */

    }

    @Override
    @Transactional
    public void actualizarE(Estudiante estudiante) {
        this.iEstudianteRepository.save(estudiante);
    }

    @Override
    @Transactional(readOnly = true)
    public Estudiante encontrarE(Integer id) {
        return this.iEstudianteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarE(Integer id) {
        this.iEstudianteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Estudiante encontrarPorEmail(String email) {
        return this.iEstudianteRepository.findByEmail(email);
    }

    @Override
    public Estudiante encontrarPorCedula(String cedula) {
        return this.iEstudianteRepository.findByCedula(cedula);
    }

    @Override
    public List<Estudiante> encontrarTodos() {
        return this.iEstudianteRepository.findAll();
    }

    private String generateRandomPassword() {
        // Genera una contraseña aleatoria
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private String generateStudentEmail(String nombre, String apellido) {
        // Divide el nombre en partes usando el espacio como delimitador
        String[] partesNombre = nombre.split(" ");

        // Toma la primera letra de cada parte del nombre
        StringBuilder iniciales = new StringBuilder();
        for (String parte : partesNombre) {
            if (!parte.isEmpty()) {
                iniciales.append(parte.charAt(0));
            }
        }

        // Genera el email usando las iniciales y el apellido
        String email = String.format("%s%s@uce.edu.ec", iniciales.toString(), apellido).toLowerCase();
        return email;
    }

}
