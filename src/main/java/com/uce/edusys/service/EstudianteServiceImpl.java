package com.uce.edusys.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.repository.IEstudianteRepository;
import com.uce.edusys.repository.modelo.Estudiante;
import com.uce.edusys.repository.modelo.Rol;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final IEstudianteRepository iEstudianteRepository;
    private final IRolRepository iRolRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteServiceImpl(IEstudianteRepository estudianteRepository, IRolRepository iRolRepository,
            PasswordEncoder passwordEncoder) {
        this.iEstudianteRepository = estudianteRepository;
        this.iRolRepository = iRolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registrarE(Estudiante estudiante) {
        Rol role = iRolRepository.findByNombre("ROLE_ESTUDIANTE");
        if (!estudiante.getRoles().contains(role)) {
            estudiante.getRoles().add(role);
        }

        // Generar email y contraseña
        String email = generateStudentEmail(estudiante.getNombre(), estudiante.getApellido());
        String password = generateRandomPassword();

        estudiante.setEmail(email);
        estudiante.setPassword(passwordEncoder.encode(password));

        this.iEstudianteRepository.save(estudiante);
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
        // Limpia y divide los nombres y apellidos
        String[] partesNombre = nombre.trim().split("\\s+");
        String[] partesApellido = apellido.trim().split("\\s+");

        // Toma la primera letra del primer nombre
        String primeraLetraNombre = partesNombre.length > 0 ? partesNombre[0].substring(0, 1) : "";
        // Toma la primera letra del segundo nombre, si existe
        String primeraLetraSegundoNombre = partesNombre.length > 1 ? partesNombre[1].substring(0, 1) : "";
        // Toma el primer apellido completo
        String primerApellido = partesApellido.length > 0 ? partesApellido[0] : "";

        // Genera el email
        String email = String.format("%s%s%s@uce.edu.ec",
                primeraLetraNombre.toLowerCase(),
                primeraLetraSegundoNombre.toLowerCase(),
                primerApellido.toLowerCase());

        return email;
    }

    @Override
    public boolean existsByCedula(String cedula) {
        return this.iEstudianteRepository.existsByCedula(cedula);
    }

}
