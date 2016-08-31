package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Partner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PartnerRepository {
    @PersistenceContext
    EntityManager em;

    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Partner p) {
        em.persist(p);
    }

    public Partner update(Partner p) {
        return em.merge(p);
    }

    public void delete(Partner p) {
        em.remove(p);
    }

    public void delete(Long id) {
        Partner p = findById(id);
        em.remove(p);
    }

    public Partner findById(Long id) {
        return em.find(Partner.class, id);
    }
}
