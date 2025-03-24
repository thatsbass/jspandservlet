package com.example.webapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String role;
    private LocalDateTime createdAt;
    private boolean active;
    
    // Default constructor
    public User() {
    }
    
    public User(String username, String email, String fullName, String role) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.active = true;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isAdmin() {
        return "ADMIN".equals(this.role);
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + 
               ", fullName=" + fullName + ", role=" + role + "]";
    }
}