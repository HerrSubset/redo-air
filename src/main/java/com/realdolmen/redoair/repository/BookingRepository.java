package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Booking;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BookingRepository {
    @PersistenceContext
    EntityManager em;

    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Booking b) {
        em.persist(b);
    }

    public Booking update(Booking b) {
        return em.merge(b);
    }

    public void delete(Booking b) {
        em.remove(b);
    }

    public void delete(Long id) {
        Booking b = findById(id);
        em.remove(b);
    }

    public Booking findById(Long id) {
        return em.find(Booking.class, id);
    }
}
