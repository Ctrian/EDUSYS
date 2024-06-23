package com.uce.edusys.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Representante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

    
@Repository
@Transactional

public class RepresentanteRepositoryImpl implements IRepresentanteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertar(Representante representante) {
        this.entityManager.persist(representante);
    }

    @Override
    public void actualizar(Representante representante) {
        this.entityManager.merge(representante);
    }

    @Override
    public Representante buscar(Integer id) {
        return this.entityManager.find(Representante.class, id);
    }

    @Override
    public void eliminar(Integer id) {
        Representante representante = this.buscar(id);
        this.entityManager.remove(representante);
    }

    @Override
    public Representante encontrarPorCedula(String cedula) {
        TypedQuery<Representante> query = this.entityManager
				.createQuery("SELECT e FROM Cliente e WHERE e.cedula = :datoCedula", Representante.class);
		query.setParameter("datoCedula", cedula);
		return query.getSingleResult();
    }

    @Override
    public List<Representante> encontrarPorCedulaRepresentados(String cedula) {
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPorCedulaRepresentados'");
    }

    @Override
    public List<Representante> encontrarRepresentantesTodos() {
        throw new UnsupportedOperationException("Unimplemented method 'encontrarRepresentantesTodos'");
    }

    @Override
    public List<Representante> encontrarPagosRepresentante(String cedula) {
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPagosRepresentante'");
    }

}
