package com.realdolmen.redoair.repository;


import com.realdolmen.redoair.domain.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public List<Category> getFilteredFlights(String departureAirport, String arrivalAirport, Date departureDate,
                                             Date returnDate, String className, Integer numberOfPeople,
                                             String airline) {

        String queryString = "SELECT c FROM Category c WHERE ";

        //append where clauses for given params
        if (departureAirport != null) {
            queryString += " c.flight.departureAirport.code LIKE :departureAirport AND ";
        }
        if (arrivalAirport != null) {
            queryString += " c.flight.arrivalAirport.code LIKE :arrivalAirport AND ";
        }
        if (className != null) {
            queryString += " c.name LIKE :className AND ";
        }
        if (airline != null) {
            queryString += " c.flight.airline.name LIKE :airline AND ";
        }


        //clean up query string
        queryString = queryString.trim();
        if (queryString.endsWith("WHERE")) {
            queryString = queryString.substring(0, queryString.length() - 5).trim();
        } else if (queryString.endsWith("AND")) {
            queryString = queryString.substring(0, queryString.length() - 3).trim();
        }

        //fill in params
        TypedQuery<Category> q = em.createQuery(queryString, Category.class);
        if (departureAirport != null) {
            q.setParameter("departureAirport", departureAirport);
        }
        if (arrivalAirport != null) {
            q.setParameter("arrivalAirport", arrivalAirport);
        }
        if (className != null) {
            q.setParameter("className", className);
        }
        if (airline != null) {
            q.setParameter("airline", airline);
        }

        List<Category> categories = q.getResultList();

        if (departureDate != null) {
            categories = clearFlightsWithInvalidDate(categories, departureDate);
        }

        return categories;
    }

    private List<Category> clearFlightsWithInvalidDate(List<Category> categories, Date d) {
        List<Category> res = new ArrayList<>();

        for (Category c: categories) {
            if (sameDate(c.getFlight().getDepartureTime(), d)) {
                res.add(c);
            }
        }

        return res;
    }

    private boolean sameDate(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
