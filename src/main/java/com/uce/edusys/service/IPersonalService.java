package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.Personal;

public interface IPersonalService {
    
    public void registrarP(Personal personal);

	public void actualizarP(Personal personal);

	public Personal encontrarP(Integer id);

	public void borrarP(Integer id);

	public Personal encontrarPorEmail(String email);

	public Personal encontrarPorCedula(String cedula);

	// encontrar todos los Personals
	public List<Personal> encontrarTodos();
}
