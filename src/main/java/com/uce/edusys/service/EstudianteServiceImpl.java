package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public EstudianteServiceImpl(IEstudianteRepository estudianteRepository, IRolRepository iRolRepository) {
        this.iEstudianteRepository = estudianteRepository;
        this.iRolRepository = iRolRepository;
    }

    @Override
    @Transactional
    public void registrarE(Estudiante estudiante) {
        Rol role = iRolRepository.findByNombre("ROLE_ESTUDIANTE");
        //representante.getRoles().add(role);
        if (!estudiante.getRoles().contains(role)) {
            estudiante.getRoles().add(role);
        }
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

}
