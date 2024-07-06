package com.uce.edusys.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.uce.edusys.repository.modelo.Representante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
    public Representante encontrarPorEmail(String email) {
        TypedQuery<Representante> query = this.entityManager
                .createQuery("SELECT e FROM Representante e WHERE e.email=:datoEmail", Representante.class);
        query.setParameter("datoEmail", email);
        List<Representante> results = query.getResultList();
        if (results.isEmpty()) {
            System.out.println("No se encontró representante con email: " + email);
            return null;
        }
        System.out.println("Se encontró representante: " + results.get(0).getEmail());
    return results.get(0);
    }

    @Override
    public List<Representante> encontrarPorCedulaRepresentados(String cedula) {
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPorCedulaRepresentados'");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Representante> encontrarTodos() {
        Query query = this.entityManager.createQuery("SELECT r FROM Representante r", Representante.class);
		return query.getResultList();
    }

    @Override
    public List<Representante> encontrarPagosRepresentante(String cedula) {
        throw new UnsupportedOperationException("Unimplemented method 'encontrarPagosRepresentante'");
    }
}
