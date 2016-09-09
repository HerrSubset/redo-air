package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.service.CustomerService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


@SessionScoped
@ManagedBean
public class LoginController implements Serializable {

    @Inject
    CustomerService customerService;

    @Inject
    private SessionController sessionController;

    private String email;
    private String password;

    private boolean isLogin;

    private String url;

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
        return "register.xhtml?url=" + url + "&faces-redirect=true";
    }

    private Customer findCustomer(String email) {
        return customerService.getCustomerByEmail(email);
    }

    //validate login
    public void validateUsernamePassword() {
        boolean valid = customerService.validate(email, password);
        if (valid) {
            HttpSession session = sessionController.getSession();
            session.setAttribute("email", email);
            if(url!=null) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect( url );
                    return; // do redirect

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        } else {
            //TODO give error message
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
