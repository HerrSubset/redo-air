package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.service.CustomerService;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@RequestScoped
@ManagedBean
public class RegisterController implements Serializable {

    @Inject
    CustomerService customerService;

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
        System.out.println("Register");
        //todo check passwordlength etc...
        String hashed = encryptPassword(password);
        if(!hashed.isEmpty() && !password.isEmpty()) {
            System.out.println("pw could be hashed");
            //password could be hashed

            Customer c = findCustomer(email);
            if(c.getId()==null) {
                System.out.println("new cust returned");
                //database returned a new customer
                c = new Customer(firstName, lastName, email);
                c.setDigest(hashed);
                Customer cc = customerService.create(c);
                //todo do login;
                return "payment.xhtml" + "faces-redirect=true";
            } else {
                System.out.println("valid cust returned");
                //database returned a valid customer
                if (c.getDigest().equals(hashed)) {
                    System.out.println("pw correct");
                    // password is correct
                    //todo do login
                    return "payment.xhtml" + "faces-redirect=true";
                } else {
                    System.out.println("pw incorrect");
                    //password is incorrect
                    //todo give error messages
                    return "register.xhtml" + "faces-redirect=true";
                }

            }
        } else {
            System.out.println("REGISTER FAILED");
            return "register.xhtml"+"faces-redirect=true";
        }
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

    private Customer findCustomer(String email) {
        return customerService.getCustomerByEmail(email);

    }
}
