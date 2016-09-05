package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.service.CustomerService;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@RequestScoped
@ManagedBean
public class LoginController implements Serializable {

    @Inject
    CustomerService customerService;

    private String email;
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

//    public String login(){
//        // TODO: 1/09/2016 do login
//
//        System.out.println("Login");
//        //todo check passwordlength etc...
//        String hashed = checkPassword(password);
//        if(!hashed.isEmpty()) {
//            System.out.println("pw could be hashed");
//            //password could be hashed
//
//            Customer c = findCustomer(email);
//            if(c.getId()==null) {
//                System.out.println("new cust returned" + c.getId() + " email =" + email);
//                //database returned a new customer
//                return "login.xhtml" + "faces-redirect=true";
//            } else {
//                System.out.println("valid cust returned");
//                //database returned a valid customer
//                if (checkPassword(password, c.getDigest())) {
//                    System.out.println("pw correct");
//                    // password is correct
//                    //todo do login
//                    return logUserIn();
//
//                } else {
//                    System.out.println("pw incorrect");
//                    //password is incorrect
//                    //todo give error messages
//                    return "login.xhtml" + "faces-redirect=true";
//                }
//
//            }
//        } else {
//            System.out.println("REGISTER FAILED");
//            return "login.xhtml" + "faces-redirect=true";
//        }
//    }

    public String register(){
        return "register.xhtml"+"faces-redirect=true";
    }

    private Customer findCustomer(String email) {
        return customerService.getCustomerByEmail(email);

    }

    public String logUserIn () {
        System.out.println("user inloggen");
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            request.login(this.email, this.password);
        } catch (ServletException e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage("Login failed."));
            System.out.println("user inloggen failed");
            return "error";
        }
        context.addMessage(null, new FacesMessage("Login succes"));
        System.out.println("user inloggen done");
        return "payment.xhtml" + "faces-redirect=true";
    }



    //validate login
    public String validateUsernamePassword() {
        boolean valid = customerService.validate(email, password);
        if (valid) {
            HttpSession session = SessionController.getSession();
            session.setAttribute("email", email);
            return "payment";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionController.getSession();
        session.invalidate();
        return "login";
    }


}
