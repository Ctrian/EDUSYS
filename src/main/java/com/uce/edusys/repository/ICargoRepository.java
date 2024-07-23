package com.uce.edusys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Cargo;

@Repository
public interface ICargoRepository extends JpaRepository<Cargo, Integer> {


}
