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
public class LoginController implements Serializable {

    @Inject
    CustomerService customerService;

    @NotNull
    private String email;
    @NotNull
    private String password;
    private String hashedPassword;
    private String salt;

    private boolean isLogin;

    @PostConstruct
    public void setUp() {
        isLogin=false;
    }

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
        // TODO: 1/09/2016 do login

        System.out.println("Login");
        //todo check passwordlength etc...
        String hashed = checkPassword(password);
        if(!hashed.isEmpty()) {
            System.out.println("pw could be hashed");
            //password could be hashed

            Customer c = findCustomer(email);
            if(c.getId()==null) {
                System.out.println("new cust returned");
                //database returned a new customer
                return "login.xhtml" + "faces-redirect=true";
            } else {
                System.out.println("valid cust returned");
                //database returned a valid customer
                if (checkPassword(password, c.getDigest())) {
                    System.out.println("pw correct");
                    // password is correct
                    //todo do login
                    return "payment.xhtml" + "faces-redirect=true";
                } else {
                    System.out.println("pw incorrect");
                    //password is incorrect
                    //todo give error messages
                    return "login.xhtml" + "faces-redirect=true";
                }

            }
        } else {
            System.out.println("REGISTER FAILED");
            return "login.xhtml" + "faces-redirect=true";
        }
    }

    public String register(){
        return "register.xhtml"+"faces-redirect=true";
    }

    public boolean checkPassword(String passwordToHash, String hashedPassword) {
        //https://github.com/jeremyh/jBCrypt

        try {
            return BCrypt.checkpw(passwordToHash, hashedPassword);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public String checkPassword(String passwordToHash) {
        //https://github.com/jeremyh/jBCrypt
        return BCrypt.hashpw(passwordToHash, BCrypt.gensalt(12));
    }

    public String getButtonString(String login, String register) {
        if(!isLogin) {
            return login;
        } else {
            return register;
        }
    }

    private Customer findCustomer(String email) {
        return customerService.getCustomerByEmail(email);

    }
}
