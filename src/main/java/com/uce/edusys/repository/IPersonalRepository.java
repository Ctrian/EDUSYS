package com.uce.edusys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Personal;

@Repository
public interface IPersonalRepository extends JpaRepository<Personal, Integer> {

    // encontrar por email
    @Query("SELECT p FROM Personal p LEFT JOIN FETCH p.roles WHERE p.email = :email")
    public Personal findByEmail(@Param("email") String email);

    // encontrar la lista de representados
    public Personal findByCedula(String cedula);

}
