package com.example.webapp.filter;


import com.example.webapp.service.AuthService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    
    private AuthService authService;
    private List<String> publicUrls;
    private List<String> adminUrls;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authService = new AuthService();
        
       
        publicUrls = Arrays.asList(
            "/login", 
            "/login.jsp", 
            "/register", 
            "/register.jsp",
            "/css/", 
            "/js/", 
            "/images/", 
            "/logout",
            "/error.jsp"
        );
        
      
        adminUrls = Arrays.asList(
            "/admin", "/admin/", "/admin.jsp", "/users", "/users.jsp"
        );
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());
        
        // Check if the requested URL is public
        boolean isPublicUrl = publicUrls.stream().anyMatch(path::startsWith);
        
        if (isPublicUrl) {
            // Allow access to public URLs
            chain.doFilter(request, response);
            return;
        }
        
        // Check if user is authenticated
        if (!authService.isAuthenticated(httpRequest)) {
            // Redirect to login page
            httpResponse.sendRedirect(contextPath + "/login.jsp");
            return;
        }
        
        // Check if URL requires admin role
        boolean isAdminUrl = adminUrls.stream().anyMatch(path::startsWith);
        
        if (isAdminUrl && !authService.isAdmin(httpRequest)) {
            // Redirect to access denied page
            httpResponse.sendRedirect(contextPath + "/access_denied.jsp");
            return;
        }
        
        // Continue with the request
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // Nothing to do
    }
}