package com.realdolmen.redoair.repository;

import com.realdolmen.redoair.domain.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CustomerRepository {
    @PersistenceContext
    EntityManager em;

    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Customer c) {
        em.persist(c);
    }

    public Customer update(Customer c) {
        return em.merge(c);
    }

    public void delete(Customer c) {
        em.remove(c);
    }

    public void delete(Long id) {
        Customer c = findById(id);
        em.remove(c);
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }



    /***********************************************************
     * Queries
     ***********************************************************/
    public List<Customer> findAll() {
        TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return q.getResultList();
    }
}
