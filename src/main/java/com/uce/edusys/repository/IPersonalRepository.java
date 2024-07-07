package com.uce.edusys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Personal;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal, Integer> {
    
    // encontrar por email
    public Personal findByEmail(String email);

    // encontrar la lista de representados
    public Personal findByCedula(String cedula);

}
