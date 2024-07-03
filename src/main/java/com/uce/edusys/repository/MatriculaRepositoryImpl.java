package com.uce.edusys.repository;

import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Matricula;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional

public class MatriculaRepositoryImpl implements IMatriculaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertar(Matricula matricula) {
        this.entityManager.persist(matricula);
    }

    @Override
    public void actualizar(Matricula matricula) {
        this.entityManager.merge(matricula);
    }

    @Override
    public Matricula buscar(Integer id) {
        return this.entityManager.find(Matricula.class, id);
    }

    @Override
    public void eliminar(Integer id) {
        Matricula matricula = this.buscar(id);
        this.entityManager.remove(matricula);
    }
    
}
