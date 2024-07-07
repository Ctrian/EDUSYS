package com.uce.edusys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.IMatriculaRepository;
import com.uce.edusys.repository.modelo.Matricula;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    public IMatriculaRepository iMatriculaRepository;

    @Override
    @Transactional
    public void registrarM(Matricula matricula) {
        this.iMatriculaRepository.save(matricula);
    }

    @Override
    public void actualizarM(Matricula matricula) {
        this.iMatriculaRepository.save(matricula);
    }

    @Override
    public Matricula encontrarM(Integer id) {
        return this.iMatriculaRepository.findById(id).orElse(null);
    }

    @Override
    public void borrarM(Integer id) {
        this.iMatriculaRepository.deleteById(id);
    }

}
