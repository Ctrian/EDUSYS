package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.OfertaAcademica;

public interface IOfertaAcademicaService {

    public void registrarOF(OfertaAcademica ofertaAcademica);

    public void actualizarOF(OfertaAcademica ofertaAcademica);

    public OfertaAcademica encontrarOF(Integer id);

    public void borrarM(Integer id);

    public List<OfertaAcademica> encontrarTodos();
}
