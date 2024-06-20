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
        // TODO Auto-generated method stub
        this.entityManager.persist(representante);
    }

    @Override
    public void actualizar(Representante representante) {
        // TODO Auto-generated method stub
        this.entityManager.merge(representante);
    }

    @Override
    public Representante buscar(Integer id) {
        // TODO Auto-generated method stub
        return this.entityManager.find(Representante.class, id);
    }

    @Override
    public void eliminar(Integer id) {
        // TODO Auto-generated method stub
        Representante representante = this.buscar(id);
        this.entityManager.remove(representante);
    }

    @Override
    public Representante encontrarPorCedula(String cedula) {
        // TODO Auto-generated method stub
        TypedQuery<Representante> query = this.entityManager
				.createQuery("SELECT e FROM Cliente e WHERE e.cedula = :datoCedula", Representante.class);
		query.setParameter("datoCedula", cedula);
		return query.getSingleResult();
    }

    @Override
    public List<Representante> encontrarPorCedulaRepresentados(String cedula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPorCedulaRepresentados'");
    }

    @Override
    public List<Representante> encontrarRepresentantesTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarRepresentantesTodos'");
    }

    @Override
    public List<Representante> encontrarPagosRepresentante(String cedula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPagosRepresentante'");
    }

}
