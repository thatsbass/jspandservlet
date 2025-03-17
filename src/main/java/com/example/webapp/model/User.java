package com.example.webapp.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String email;
    private String message;
    
    // Default constructor
    public User() {
    }
    
    // Constructor with fields
    public User(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", message=" + message + "]";
    }
}