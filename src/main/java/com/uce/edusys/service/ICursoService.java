package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.Curso;

public interface ICursoService {

    public void registrarC(Curso curso);

    public void actualizarC(Curso curso);

    public Curso encontrarC(Integer id);

    public void borrarC(Integer id);

    public List<Curso> encontrarTodos();

    public List<Curso> encontrarTodos4ID(List<Integer> cursoIds);
}
