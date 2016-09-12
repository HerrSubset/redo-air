package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.service.CustomerService;
import org.hibernate.validator.constraints.Email;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.*;
import java.io.Serializable;

@ViewScoped
@ManagedBean
public class RegisterController implements Serializable {

    @Inject
    CustomerService customerService;

    @Inject
    private SessionController sessionController;

    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min=2)
    private String password;

    @NotNull
    @Size(min=2, max = 20)
    private String firstName;

    @NotNull
    @Size(min=2, max = 240)
    private String lastName;

    private String url;
    private String cid;

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

    public void register(){
        String redirectUrl="register.jsf";

        Customer c = customerService.getCustomerByEmail(password);
        if (c.getId()==null) {
            // geen waarde in de database
            if(customerService.saveCustomer(firstName, lastName, password, email)) {
                HttpSession session = sessionController.getSession();
                session.setAttribute("email", email);

                if(url!=null) {
                    redirectUrl = url;

                } else {
                    redirectUrl = "index.jsf";
                }
            }
        }

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void login(){
        String redirectUrl="login.jsf";

        Customer c = customerService.getCustomerByEmail(password);
        if (c.getId()==null) {
            // geen waarde in de database
            if(customerService.saveCustomer(firstName, lastName, password, email)) {
                HttpSession session = sessionController.getSession();
                session.setAttribute("email", email);

                if(url!=null) {
                    redirectUrl = url;

                } else {
                    redirectUrl = "index.jsf";
                }
            }
        }

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
