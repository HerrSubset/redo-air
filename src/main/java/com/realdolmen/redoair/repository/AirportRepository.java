package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Airport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AirportRepository {
    @PersistenceContext
    EntityManager em;

    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Airport a) {
        em.persist(a);
    }

    public Airport update(Airport a) {
        return em.merge(a);
    }

    public void delete(Airport a) {
        em.remove(a);
    }

    public void delete(Long id) {
        Airport a = findById(id);
        em.remove(a);
    }

    public Airport findById(Long id) {
        return em.find(Airport.class, id);
    }



    /***********************************************************
     * Queries
     ***********************************************************/
    public List<Airport> findAll() {
        TypedQuery<Airport> q = em.createQuery("SELECT a FROM Airport a", Airport.class);
        return q.getResultList();
    }
}
