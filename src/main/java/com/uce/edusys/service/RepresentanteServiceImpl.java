package com.uce.edusys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edusys.repository.modelo.Representante;
import com.uce.edusys.repository.IRepresentanteRepository;

@Service

public class RepresentanteServiceImpl implements IRepresentanteService {

    @Autowired
    private IRepresentanteRepository iRepresentanteRepository;

    @Override
    public void registrarR(Representante representante) {
        // TODO Auto-generated method stub
        this.iRepresentanteRepository.insertar(representante);
    }

    @Override
    public void actualizarR(Representante representante) {
        // TODO Auto-generated method stub
        this.iRepresentanteRepository.actualizar(representante);
    }

    @Override
    public Representante encontrarR(Integer id) {
        // TODO Auto-generated method stub
        return this.iRepresentanteRepository.buscar(id);
    }

    @Override
    public void borrarR(Integer id) {
        // TODO Auto-generated method stub
        this.iRepresentanteRepository.eliminar(id);
    }

    @Override
    public Representante encontrarPorCedula(String cedula) {
        // TODO Auto-generated method stub
        return this.iRepresentanteRepository.encontrarPorCedula(cedula);
    }
    
}
