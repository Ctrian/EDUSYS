package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.IRepresentanteRepository;

@Service

public class RepresentanteServiceImpl implements IRepresentanteService {

    @Autowired
    public IRepresentanteRepository iRepresentanteRepository;

    @Override
    public void registrarR(Representante representante) {
        this.iRepresentanteRepository.insertar(representante);
    }

    @Override
    public void actualizarR(Representante representante) {
        this.iRepresentanteRepository.actualizar(representante);
    }

    @Override
    public Representante encontrarR(Integer id) {
        return this.iRepresentanteRepository.buscar(id);
    }

    @Override
    public void borrarR(Integer id) {
        this.iRepresentanteRepository.eliminar(id);
    }

    @Override
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
