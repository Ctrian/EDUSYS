package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.ICursoRepository;
import com.uce.edusys.repository.modelo.Curso;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    public ICursoRepository iCursoRepository; 

    @Override
    @Transactional
    public void registrarC(Curso curso) {
        this.iCursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void actualizarC(Curso curso) {
        this.iCursoRepository.save(curso);
    }

    @Override
    @Transactional(readOnly = true)
    public Curso encontrarC(Integer id) {
        return this.iCursoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarC(Integer id) {
        this.iCursoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> encontrarTodos() {
        return this.iCursoRepository.findAll();
    }

    @Override
    public List<Curso> encontrarTodos4ID(List<Integer> cursoIds) {
        return this.iCursoRepository.findAllById(cursoIds);
    }
    
}
