package com.uce.edusys.service;

import com.uce.edusys.repository.modelo.Matricula;

public interface IMatriculaService {

	public void registrarM(Integer ofertaAcademicaId, Integer estudianteId, Integer representanteId);

	public void actualizarM(Matricula matricula);

	public Matricula encontrarM(Integer id);

	public void borrarM(Integer id);
}
