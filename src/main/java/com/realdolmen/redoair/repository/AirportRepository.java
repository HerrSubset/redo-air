package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Airport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
