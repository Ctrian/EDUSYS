package com.uce.edusys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.repository.IPersonalRepository;
import com.uce.edusys.repository.modelo.Personal;
import com.uce.edusys.repository.modelo.Rol;

@Service    
public class PersonalServiceImpl implements IPersonalService {

    private final IPersonalRepository iPersonalRepository;
    private final IRolRepository iRolRepository;
    // private final PasswordEncoder passwordEncoder;

    public PersonalServiceImpl(IPersonalRepository iPersonalRepository, IRolRepository iRolRepository) {
        this.iPersonalRepository = iPersonalRepository;
        this.iRolRepository = iRolRepository;
    }

    @Override
    @Transactional
    public void registrarP(Personal personal) {
        Rol role = iRolRepository.findByNombre("ROLE_PERSONAL");
        // representante.getRoles().add(role);
        if (!personal.getRoles().contains(role)) {
            personal.getRoles().add(role);
        }
        this.iPersonalRepository.save(personal);
    }

    @Override
    @Transactional
    public void actualizarP(Personal personal) {
        this.iPersonalRepository.save(personal);
    }

    @Override
    @Transactional(readOnly = true)
    public Personal encontrarP(Integer id) {
        return this.iPersonalRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarP(Integer id) {
        this.iPersonalRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Personal encontrarPorEmail(String email) {
        return this.iPersonalRepository.findByEmail(email);
    }

    @Override
    public Personal encontrarPorCedula(String cedula) {
        return this.iPersonalRepository.findByCedula(cedula);
    }

    @Override
    public List<Personal> encontrarTodos() {
        return this.iPersonalRepository.findAll();
    }

}
