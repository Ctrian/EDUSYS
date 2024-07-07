package com.uce.edusys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Representante;

@Repository
public interface IRepresentanteRepository extends JpaRepository<Representante, Integer> {

    // encontrar por email
    public Representante findByEmail(String email);

    // encontrar la lista de representados
    public Representante findByCedula(String cedula);

    // encontrar todos los pagos
    // public List<Representante> encontrarPagosRepresentante(String cedula);
}