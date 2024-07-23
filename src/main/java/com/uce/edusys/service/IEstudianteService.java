package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.Estudiante;

public interface IEstudianteService {

    public void registrarE(Estudiante estudiante);

	public void actualizarE(Estudiante estudiante);

	public Estudiante encontrarE(Integer id);

	public void borrarE(Integer id);

	public Estudiante encontrarPorEmail(String email);

	public Estudiante encontrarPorCedula(String cedula);

	// encontrar todos los Estudiantes
	public List<Estudiante> encontrarTodos();

	boolean existsByCedula(String cedula);
}
