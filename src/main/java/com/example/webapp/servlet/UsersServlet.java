package com.example.webapp.servlet;

import com.example.webapp.dao.UserDAO;
import com.example.webapp.model.User;
import com.example.webapp.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final AuthService authService;
    private final UserDAO userDAO;
    
    public UsersServlet() {
        this.authService = new AuthService();
        this.userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is authenticated and is admin
        if (!authService.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        if (!authService.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/access_denied.jsp");
            return;
        }
        
        // Get all users
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        
        // Forward to users page
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Check if user is authenticated and is admin
        if (!authService.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        if (!authService.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/access_denied.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        
        if ("create".equals(action)) {
            // Create new user
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String role = request.getParameter("role");
            
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setFullName(fullName);
            newUser.setRole(role);
            
            boolean success = userDAO.createUser(newUser, password);
            
            if (success) {
                request.setAttribute("successMessage", "User created successfully");
            } else {
                request.setAttribute("errorMessage", "Failed to create user");
            }
            
        } else if ("update".equals(action)) {
            // Update user
            Long userId = Long.parseLong(request.getParameter("id"));
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String role = request.getParameter("role");
            String active = request.getParameter("active");
            
            Optional<User> optionalUser = userDAO.findById(userId);
            
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setEmail(email);
                user.setFullName(fullName);
                user.setRole(role);
                user.setActive("on".equals(active));
                
                boolean success = userDAO.updateUser(user);
                
                if (success) {
                    request.setAttribute("successMessage", "User updated successfully");
                } else {
                    request.setAttribute("errorMessage", "Failed to update user");
                }
            } else {
                request.setAttribute("errorMessage", "User not found");
            }
            
        } else if ("delete".equals(action)) {
            // Delete user
            Long userId = Long.parseLong(request.getParameter("id"));
            
            boolean success = userDAO.deleteUser(userId);
            
            if (success) {
                request.setAttribute("successMessage", "User deleted successfully");
            } else {
                request.setAttribute("errorMessage", "Failed to delete user");
            }
        }
        
        // Get all users
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        
        // Forward to users page
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}