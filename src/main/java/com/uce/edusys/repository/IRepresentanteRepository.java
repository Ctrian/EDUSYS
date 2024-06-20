package com.uce.edusys.repository;

import java.util.List;

import com.uce.edusys.repository.modelo.Representante;

public interface IRepresentanteRepository {
    
	public void insertar(Representante representante);

	public void actualizar(Representante representante);

	public Representante buscar(Integer id);

	public void eliminar(Integer id);

    // encontrar por cédula
    public Representante encontrarPorCedula(String cedula);

    // encontrar la lista de representados
    public List<Representante> encontrarPorCedulaRepresentados(String cedula);

    // encontrar todos los representantes
    public List<Representante> encontrarRepresentantesTodos();

    // encontrar todos los pagos
    public List<Representante> encontrarPagosRepresentante(String cedula);
}