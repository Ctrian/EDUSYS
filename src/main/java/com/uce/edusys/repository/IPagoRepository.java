package com.uce.edusys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Pago;

@Repository
public interface IPagoRepository extends JpaRepository<Pago, Integer> {

    public List<Pago> findByMatriculaId(Integer id);

    public List<Pago> findByFacturaId(Integer id);
    
    // public List<Pago> findByRepresentanteId(Integer id);
}
