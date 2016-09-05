package com.realdolmen.redoair.service;
import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.repository.CustomerRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@LocalBean
@Stateless
public class CustomerService {
    @Inject
    CustomerRepository repo;

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }
    public Customer getCustomerById(Long id) {
        return repo.findById(id);
    }
    public Customer create (Customer c) {
        repo.create(c);
        return repo.findById(c.getId());
    }

    public Customer getCustomerByEmail(String email) {
        return repo.findCustomerByEmail(email);
    }
}
