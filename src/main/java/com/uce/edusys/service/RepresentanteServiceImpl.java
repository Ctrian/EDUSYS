package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.repository.IRepresentanteRepository;
import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.modelo.Rol;

@Service
public class RepresentanteServiceImpl implements IRepresentanteService {

    private final IRepresentanteRepository iRepresentanteRepository;
    private final IRolRepository iRolRepository;

    @Autowired
    public RepresentanteServiceImpl(IRepresentanteRepository iRepresentanteRepository, IRolRepository iRolRepository) {
        this.iRepresentanteRepository = iRepresentanteRepository;
        this.iRolRepository = iRolRepository;
    }

    @Override
    @Transactional
    public void registrarR(Representante representante) {
        Rol role = iRolRepository.findByNombre("ROLE_REPRESENTANTE");
        // representante.getRoles().add(role);
        if (!representante.getRoles().contains(role)) {
            representante.getRoles().add(role);
        }
        this.iRepresentanteRepository.save(representante);
    }

    @Override
    @Transactional
    public void actualizarR(Representante representante) {
        this.iRepresentanteRepository.save(representante);
    }

    @Override
    @Transactional(readOnly = true)
    public Representante encontrarR(Integer id) {
        return this.iRepresentanteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarR(Integer id) {
        this.iRepresentanteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Representante encontrarPorEmail(String email) {
        return this.iRepresentanteRepository.findByEmail(email);
    }

    @Override
    public Representante encontrarPorCedula(String cedula) {
        return this.iRepresentanteRepository.findByCedula(cedula);
    }

    @Override
    public List<Representante> encontrarTodos() {
        return this.iRepresentanteRepository.findAll();
    }

    /*
     * @Override
     * public Page<Representante> encontrarTodos(int page, int size) {
     * Pageable pageable = PageRequest.of(page, size);
     * return this.iRepresentanteRepository.findAll(pageable);
     * }
     */

    // @Override
    // public Page<Representante> encontrarTodos(Pageable pageable) {
    // return null;
    // }

}
