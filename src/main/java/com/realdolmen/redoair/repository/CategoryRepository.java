package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CategoryRepository {
    @PersistenceContext
    EntityManager em;

    /***********************************************************
     * CRUD operations
     ***********************************************************/
    public void create(Category c) {
        em.persist(c);
    }

    public Category update(Category c) {
        return em.merge(c);
    }

    public void delete(Category c) {
        em.remove(c);
    }

    public void delete(Long id) {
        Category c = findById(id);
        em.remove(c);
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }



    /***********************************************************
     * Queries
     ***********************************************************/

    public List<String> findAllNames() {
        TypedQuery<String> q = em.createQuery("SELECT DISTINCT c.name FROM Category c", String.class);
        return q.getResultList();
    }

    public List<Category> findAll() {
        TypedQuery<Category> q = em.createQuery("SELECT c FROM Category c", Category.class);
        return q.getResultList();
    }
}
