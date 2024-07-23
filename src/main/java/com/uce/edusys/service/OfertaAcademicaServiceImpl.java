package com.uce.edusys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uce.edusys.repository.IOfertaAcademicaRepository;
import com.uce.edusys.repository.modelo.OfertaAcademica;

@Service
public class OfertaAcademicaServiceImpl implements IOfertaAcademicaService {

    @Autowired
    public IOfertaAcademicaRepository iOfertaAcademicaRepository;

    @Override
    @Transactional
    public void registrarOF(OfertaAcademica ofertaAcademica) {
        this.iOfertaAcademicaRepository.save(ofertaAcademica);
    }

    @Override
    @Transactional
    public void actualizarOF(OfertaAcademica ofertaAcademica) {
        this.iOfertaAcademicaRepository.save(ofertaAcademica);
    }

    @Override
    @Transactional(readOnly = true)
    public OfertaAcademica encontrarOF(Integer id) {
        return this.iOfertaAcademicaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void borrarM(Integer id) {
        this.iOfertaAcademicaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfertaAcademica> encontrarTodos() {
        return this.iOfertaAcademicaRepository.findAll();
    }
    
}
