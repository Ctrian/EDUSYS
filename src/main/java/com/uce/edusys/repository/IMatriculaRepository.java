package com.uce.edusys.repository;

import com.uce.edusys.repository.modelo.Matricula;

public interface IMatriculaRepository {

    public void insertar(Matricula matricula);

	public void actualizar(Matricula matricula);

	public Matricula buscar(Integer id);

	public void eliminar(Integer id);
}
