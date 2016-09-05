package com.realdolmen.redoair.service;
import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.repository.CustomerRepository;
import org.mindrot.jbcrypt.BCrypt;

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

    public boolean validate(String email, String password) {

        Customer c = repo.findCustomerByEmail(email);
        if (c.getId()!=null) {
            //valid customer
            if (checkPassword(password, c.getDigest())) {
                //password is correct
                return true;
            }
        }
        return false;
    }


    public boolean checkPassword(String passwordToHash, String hashedPassword) {
        //https://github.com/jeremyh/jBCrypt
        try {
            return BCrypt.checkpw(passwordToHash, hashedPassword);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public String hashPassword(String passwordToHash) {
        //https://github.com/jeremyh/jBCrypt
        return BCrypt.hashpw(passwordToHash, BCrypt.gensalt(12));
    }
}
