package com.example.webapp.servlet;

import java.io.IOException;

import com.example.webapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public UserServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Forward to the form page if someone accesses via GET
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get parameters from the form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        
        // Create a User object
        User user = new User(name, email, message);
        
        // Store the User object in the request attribute
        request.setAttribute("user", user);
        
        // Forward to the result page
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}