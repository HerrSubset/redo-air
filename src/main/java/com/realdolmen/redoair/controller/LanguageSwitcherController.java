package com.realdolmen.redoair.controller;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created by NHOBB65 on 23/08/2016.
 */

@ManagedBean
@SessionScoped
public class LanguageSwitcherController implements Serializable {

    private static final long serialVersionUID = 2756934361134603857L;
    private static final Logger LOG = Logger.getLogger(LanguageSwitcherController.class.getName());

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}