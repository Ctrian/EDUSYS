package com.uce.edusys.service;

import java.util.List;

import com.uce.edusys.repository.modelo.Cargo;

public interface ICargoService {

    public void registrarC(Cargo cargo);

    public void actualizarC(Cargo cargo);

    public Cargo encontrarC(Integer id);

    public void borrarC(Integer id);

    public List<Cargo> encontrarTodos();
}
