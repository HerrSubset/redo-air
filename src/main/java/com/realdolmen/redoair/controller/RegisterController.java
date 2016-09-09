package com.realdolmen.redoair.controller;

import com.realdolmen.redoair.domain.Customer;
import com.realdolmen.redoair.service.CustomerService;
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
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
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
//        return "login.xhtml"+"faces-redirect=true";
//    }

    public String register(){
        //todo check passwordlength etc...
        System.out.println("Register" + url);

        Customer c = customerService.getCustomerByEmail(password);
        if (c.getId()==null) {
            // geen waarde in de database
            if(customerService.saveCustomer(firstName, lastName, password, email)) {
                HttpSession session = sessionController.getSession();
                session.setAttribute("email", email);

                if(url!=null) {
                    try {
                        return url + "faces-redirect=true";

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return "index.xhtml" + "faces-redirect=true";
            }
        }
        return "register.xhtml" + "faces-redirect=true";
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
        System.out.println("url:" + url);
        this.url = url;
    }
}
