package com.uce.edusys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Representante;

@Repository
public interface IRepresentanteRepository extends JpaRepository<Representante, Integer> {

    // encontrar por email y sus relaciones
    @Query("SELECT r FROM Representante r LEFT JOIN FETCH r.roles WHERE r.email = :email")
    public Representante findByEmail(@Param("email")String email);

    // encontrar la lista de representados
    public Representante findByCedula(String cedula);

    // encontrar todos los pagos
    // public List<Representante> encontrarPagosRepresentante(String cedula);
}