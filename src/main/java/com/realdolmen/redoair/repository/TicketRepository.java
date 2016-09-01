package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TicketRepository {
    @PersistenceContext
    EntityManager em;

    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Ticket t) {
        em.persist(t);
    }

    public Ticket update(Ticket t) {
        return em.merge(t);
    }

    public void delete(Ticket t) {
        em.remove(t);
    }

    public void delete(Long id) {
        Ticket t = findById(id);
        em.remove(t);
    }

    public Ticket findById(Long id) {
        return em.find(Ticket.class, id);
    }
}
