package com.uce.edusys.service;

import com.uce.edusys.repository.modelo.Representante;

public interface IRepresentanteService {

    public void registrarR(Representante representante);

	public void actualizarR(Representante representante);

	public Representante encontrarR(Integer id);

	public void borrarR(Integer id);

	public Representante encontrarPorCedula(String cedula);

	//public List<Representante> encontrarPorCedulaList(String cedula);

	// encontrar todos los clientes
	// public List<Representante> buscarTodos();

}
