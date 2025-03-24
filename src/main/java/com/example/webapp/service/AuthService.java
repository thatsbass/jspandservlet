package com.example.webapp.service;

import com.example.webapp.dao.UserDAO;
import com.example.webapp.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

public class AuthService {
    private static final String USER_SESSION_KEY = "authenticatedUser";
    private final UserDAO userDAO;
    
    public AuthService() {
        this.userDAO = new UserDAO();
    }
    
    // Login user
    public boolean login(String username, String password, HttpServletRequest request) {
        Optional<User> optionalUser = userDAO.authenticate(username, password);
        
        if (optionalUser.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute(USER_SESSION_KEY, optionalUser.get());
            return true;
        }
        
        return false;
    }
    
    // Logout user
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    
    // Get the current authenticated user
    public Optional<User> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute(USER_SESSION_KEY);
            if (user != null) {
                return Optional.of(user);
            }
        }
        
        return Optional.empty();
    }
    
    // Check if user is authenticated
    public boolean isAuthenticated(HttpServletRequest request) {
        return getCurrentUser(request).isPresent();
    }
    
    // Check if user has admin role
    public boolean isAdmin(HttpServletRequest request) {
        Optional<User> optionalUser = getCurrentUser(request);
        return optionalUser.isPresent() && optionalUser.get().isAdmin();
    }
    
    // Check if user has specified role
    public boolean hasRole(HttpServletRequest request, String role) {
        Optional<User> optionalUser = getCurrentUser(request);
        return optionalUser.isPresent() && role.equals(optionalUser.get().getRole());
    }
}