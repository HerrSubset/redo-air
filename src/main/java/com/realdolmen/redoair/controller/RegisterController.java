package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.service.CustomerService;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@RequestScoped
@ManagedBean
public class RegisterController implements Serializable {

    @Inject
    CustomerService customerService;

    @Inject
    private SessionController sessionController;

    @NotNull
    private String email;
    @NotNull
    private String password;
    private String hashedPassword;
    private String salt;

    private String firstName;
    private String lastName;

    private boolean isLogin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login(){
        return "login.xhtml"+"faces-redirect=true";
    }

    public String register(){
        //todo check passwordlength etc...
        System.out.println("Register");

        Customer c = customerService.getCustomerByEmail(password);
        if (c.getId()==null) {
            // geen waarde in de database
            if(customerService.saveCustomer(firstName, lastName, password, email)) {
                HttpSession session = sessionController.getSession();
                session.setAttribute("email", email);
                return "payment.xhtml" + "faces-redirect=true";
            }
        }
        return "register.xhtml" + "faces-redirect=true";
    }

    public String encryptPassword(String passwordToHash) {
        //https://github.com/jeremyh/jBCrypt
        //https://mvnrepository.com/artifact/org.mindrot/jbcrypt
        // gensalt's log_rounds parameter determines the complexity
        // the work factor is 2**log_rounds, and the default is 10
        return BCrypt.hashpw(passwordToHash, BCrypt.gensalt(12));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
