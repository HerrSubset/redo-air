package com.realdolmen.redoair.controller;

import java.io.IOException;
import java.net.URLEncoder;
import javax.inject.Inject;
import javax.servlet.Filter;
        import javax.servlet.FilterChain;
        import javax.servlet.FilterConfig;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter")
public class WebAuthorizationController implements Filter {
    @Inject
    SessionController sessionController;

    public WebAuthorizationController() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            // do next filer OR send to login page
            String reqURI = reqt.getRequestURI();
            if ( (reqURI.contains("login.jsf"))
                    || (ses != null && ses.getAttribute("email") != null)
                    || reqURI.contains("javax.faces.resource")
                    || reqURI.equals("/redo-air/")
                    || reqURI.contains("index.jsf")
                    || reqURI.contains("register.jsf")
                    || reqURI.contains("flights/search.jsf")
                    || reqURI.contains("flights/details")) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(reqt.getContextPath() + "/login.jsf"+"?url=" + URLEncoder.encode(reqt.getRequestURI(), "UTF-8"));
//                resp.sendRedirect(reqt.getContextPath());
            }
        } catch (Exception e) {
//            System.out.println( "Exception");
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}