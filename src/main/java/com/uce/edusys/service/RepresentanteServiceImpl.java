package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.modelo.Rol;
import com.uce.edusys.configuracion.seguridad.IRolRepository;
import com.uce.edusys.repository.IRepresentanteRepository;

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
        if (!representante.getRoles().contains(role)) {
            representante.getRoles().add(role);
        }
        this.iRepresentanteRepository.insertar(representante);
    }

    @Override
    @Transactional
    public void actualizarR(Representante representante) {
        this.iRepresentanteRepository.actualizar(representante);
    }

    @Override
    @Transactional(readOnly = true)
    public Representante encontrarR(Integer id) {
        return this.iRepresentanteRepository.buscar(id);
    }

    @Override
    @Transactional
    public void borrarR(Integer id) {
        this.iRepresentanteRepository.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Representante encontrarPorEmail(String email) {
        return this.iRepresentanteRepository.encontrarPorEmail(email);
    }

    @Override
    public List<Representante> encontrarPorCedulaList(String cedula) {
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPorCedulaList'");
    }

    @Override
    public List<Representante> encontrarTodos() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Page<Representante> encontrarTodos(Pageable pageable) {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
