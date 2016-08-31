package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Region;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RegionRepository {
    @PersistenceContext
    EntityManager em;   //keep it on package scope so tests can create new entity managers for it


    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Region r) {
        em.persist(r);
    }

    public Region update(Region r) {
        return em.merge(r);
    }

    public void delete(Region r) {
        em.remove(r);
    }

    public void delete(Long id) {
        Region r = findById(id);
        em.remove(r);
    }

    public Region findById(Long id) {
        return em.find(Region.class, id);
    }


    /***********************************************************
     * Queries
     ***********************************************************/
}
