package com.uce.edusys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.SolicitudMatricula;

@Repository
public interface ISolicitudMatriculaRepository extends JpaRepository<SolicitudMatricula, Integer> {
    List<SolicitudMatricula> findByEstado(String estado);
}
