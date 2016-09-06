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
import javax.servlet.http.HttpSession;
import java.io.Serializable;


@RequestScoped
@ManagedBean
public class LoginController implements Serializable {

    @Inject
    CustomerService customerService;

    @Inject
    private SessionController sessionController;

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

    public String register(){
        return "register.xhtml?faces-redirect=true";
    }

    private Customer findCustomer(String email) {
        return customerService.getCustomerByEmail(email);

    }



    //validate login
    public String validateUsernamePassword() {
        boolean valid = customerService.validate(email, password);
        if (valid) {
            HttpSession session = sessionController.getSession();
            session.setAttribute("email", email);
            return "payment";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = sessionController.getSession();
        session.invalidate();
        return "index";
    }

    public String logIn() {
        return "login.xhtml?faces-redirect=true";
    }

    public String logInOut () {
        HttpSession session = sessionController.getSession();
        if(session.getAttribute("email")!=null) {
            logout();
            return "index.xhtml";
        } else {
            return "login.xhtml?faces-redirect=true";
        }
    }
}
