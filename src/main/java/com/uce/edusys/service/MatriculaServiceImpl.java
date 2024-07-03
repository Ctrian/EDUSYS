package com.uce.edusys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edusys.repository.IMatriculaRepository;
import com.uce.edusys.repository.modelo.Matricula;

@Service

public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    public IMatriculaRepository iMatriculaRepository;

    @Override
    public void registrarM(Matricula matricula) {
        this.iMatriculaRepository.insertar(matricula);
    }

    @Override
    public void actualizarM(Matricula matricula) {
        this.iMatriculaRepository.actualizar(matricula);
    }

    @Override
    public Matricula encontrarM(Integer id) {
        return this.iMatriculaRepository.buscar(id);
    }

    @Override
    public void borrarM(Integer id) {
        this.iMatriculaRepository.eliminar(id);
    }

}
