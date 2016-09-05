package com.realdolmen.redoair.controller;

import java.io.IOException;
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

    public WebAuthorizationController() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {

//            HttpServletRequest reqt = (HttpServletRequest) request;
//            HttpServletResponse resp = (HttpServletResponse) response;
//            HttpSession ses = reqt.getSession(false);
//
//            String reqURI = reqt.getRequestURI();
//
//            if (reqURI.indexOf("/login.xhtml") >= 0) {
//
//                chain.doFilter(request, response);
//            } else {
//                resp.sendRedirect(reqt.getContextPath() + "/login.jsf");
//            }

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            try {
            } catch (Exception e) {
            };


            // do next filer OR send to login page
            String reqURI = reqt.getRequestURI();
//            boolean resourceRequest = reqURI.startsWith(reqt.getContextPath() + "/faces" + ResourceHandler.RESOURCE_IDENTIFIER);
            if ( (reqURI.contains("login.jsf"))
                    || (ses != null && ses.getAttribute("email") != null)
                    || reqURI.contains("javax.faces.resource")
                    || reqURI.equals("/redo-air/")
                    || reqURI.contains("index.jsf")
                    || reqURI.contains("flights/search.jsf")
                    || reqURI.contains("flights/details")) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(reqt.getContextPath() + "/login.jsf");
            }
        } catch (Exception e) {
            System.out.println( "Exception" + e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}