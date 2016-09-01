package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Flight;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class FlightRepository {
    @PersistenceContext
    EntityManager em;

    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Flight f) {
        em.persist(f);
    }

    public Flight update(Flight f) {
        return em.merge(f);
    }

    public void delete(Flight f) {
        em.remove(f);
    }

    public void delete(Long id) {
        Flight f = findById(id);
        em.remove(f);
    }

    public Flight findById(Long id) {
        return em.find(Flight.class, id);
    }



    /***********************************************************
     * Queries
     ***********************************************************/
    public List<Flight> findAll() {
        TypedQuery<Flight> q = em.createQuery("SELECT f FROM Flight f", Flight.class);
        return q.getResultList();
    }
}
