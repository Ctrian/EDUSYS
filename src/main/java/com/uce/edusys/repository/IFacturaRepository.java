package com.uce.edusys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Factura;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Integer>{

}
