package com.example.webapp.servlet;

import com.example.webapp.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final AuthService authService;
    
    public LoginServlet() {
        this.authService = new AuthService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // If user is already authenticated, redirect to dashboard
        if (authService.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            return;
        }
        
        // Forward to login page
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (authService.login(username, password, request)) {
            // Redirect to dashboard on successful login
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        } else {
            // Add error message and forward back to login page
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}