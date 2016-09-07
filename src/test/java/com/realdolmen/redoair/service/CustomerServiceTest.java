package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repo;
    private List<Customer> customers;
    private Customer customer;
    private Long id;
    private String email="";
    private String pw="";

    @Before
    public void setUp() {
        customers = new ArrayList<>();
    }

    @Test
    public void getAllCustomerReturnsListReturnedByRepo() {
        Mockito.when(repo.findAll()).thenReturn(customers);
        Assert.assertSame(customers, service.getAllCustomers());
    }

    @Test
    public void getCustomerReturnsItemReturnedByRepo() {
        Mockito.when(repo.findById(id)).thenReturn(customer);
        Assert.assertSame(customer, service.getCustomerById(id));
    }

    @Test
    public void getCustomerByEmailReturnsItemReturnedByRepo() {
        Mockito.when(repo.findCustomerByEmail(email)).thenReturn(customer);
        Assert.assertSame(customer, service.getCustomerByEmail(email));
    }

}
