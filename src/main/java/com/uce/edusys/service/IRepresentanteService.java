package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.Representante;

public interface IRepresentanteService {

	public void registrarR(Representante representante);

	public void actualizarR(Representante representante);

	public Representante encontrarR(Integer id);

	public void borrarR(Integer id);

	public Representante encontrarPorEmail(String email);

	public Representante encontrarPorCedula(String cedula);

	// encontrar todos los Representantes
	public List<Representante> encontrarTodos();

	// public Page<Representante> encontrarTodos(Pageable pageable);

}
