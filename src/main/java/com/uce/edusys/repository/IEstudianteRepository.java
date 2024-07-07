package com.uce.edusys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Estudiante;
@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Integer> {
    
    // encontrar por email
    public Estudiante findByEmail(String email);

    // encontrar la lista de representados
    public Estudiante findByCedula(String cedula);
    
}
