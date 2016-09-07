package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.service.CustomerService;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;


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
        return "register.xhtml?faces-redirect=true";
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
//            System.out.println("redirect...");
            if(url!=null) {
                url=url.substring(9);
//                url=url.replace("jsf", "xhtml");
                url="/redo-air" + url;
                System.out.println("url..." + url);
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect( url );
//                    System.out.println("FacesContext.getCurrentInstance().getExternalContext().redirect(url); + "+ url);
                    return; // do redirect

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("payment.jsf");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
//            return "login";
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
//        System.out.println("URL: " + url);
        this.url = url;
    }
}
