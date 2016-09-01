package com.realdolmen.redoair.controller;

import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Created by NHOBB65 on 1/09/2016.
 */

@RequestScoped
@ManagedBean
public class LoginController implements Serializable {

    @NotNull
    private String email;
    @NotNull
    private String password;
    private String hashedPassword;
    private String salt;

    private boolean isLogin;

    @PostConstruct
    public void setUp() {
        isLogin=true;
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
        if(true) {
            return "error";
        } else {
            return "succes";
        }
    }

    public String register(){
        // TODO: 1/09/2016 do register
        if(true) {
            return "error";
        } else {
            return "succes";
        }
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean login) {
        isLogin = login;
    }

    public void toggleIsLogin() {
        isLogin = !isLogin;
    }

    public String doAction() {
        encryptPassword(password);
        if (isLogin) {
            return login();
        } else {
            return register();
        }
    }

    public String encryptPassword(String passwordToHash) {
        //https://github.com/jeremyh/jBCrypt
        //https://mvnrepository.com/artifact/org.mindrot/jbcrypt
        // gensalt's log_rounds parameter determines the complexity
        // the work factor is 2**log_rounds, and the default is 10
        String hashed = BCrypt.hashpw(passwordToHash, BCrypt.gensalt(12));

        // Check that an unencrypted password matches one that has
        // previously been hashed
        if (BCrypt.checkpw(passwordToHash, hashed)) {
            System.out.println("It matches");
            return hashed;
        }
        else {
            System.out.println("It does not match");
            return null;
        }
    }
}
