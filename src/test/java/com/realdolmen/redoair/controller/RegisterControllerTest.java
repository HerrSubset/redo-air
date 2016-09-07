package com.realdolmen.redoair.controller;


import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.repository.CustomerRepository;
import com.realdolmen.redoair.service.CustomerService;
import com.realdolmen.redoair.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;


public class RegisterControllerTest extends JpaPersistenceTest {
    RegisterController controller;

    @Before
    public void setUp() {
        controller = new RegisterController();
    }

    @Test
    public void shouldSetAndReturnEmail() {
        controller.setEmail(",kmlsd,ml");
        assertEquals(controller.getEmail(), ",kmlsd,ml");
    }

    @Test
    public void shouldSetAndReturnPassword() {
        controller.setPassword(",kmlsd,ml");
        assertEquals(controller.getPassword(), ",kmlsd,ml");
    }

    @Test
    public void shouldSetAndReturnFirstName() {
        controller.setFirstName(",kmlsd,ml");
        assertEquals(controller.getFirstName(), ",kmlsd,ml");
    }

    @Test
    public void shouldSetAndReturnLastName() {
        controller.setLastName(",kmlsd,ml");
        assertEquals(controller.getLastName(), ",kmlsd,ml");
    }
}



