<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .header {
            background-color: #333;
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .user-info {
            display: flex;
            align-items: center;
        }
        .user-info span {
            margin-right: 15px;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
        }
        .card {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 20px;
            margin-bottom: 20px;
        }
        .button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            padding: 8px 15px;
            border-radius: 4px;
        }
        .button:hover {
            background-color: #45a049;
        }
        .button.secondary {
            background-color: #f44336;
        }
        .button.secondary:hover {
            background-color: #d32f2f;
        }
        .menu {
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .menu a {
            margin-right: 15px;
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }
        .menu a:hover {
            color: #4CAF50;
        }
    </style>
</head>
<body>
    <div class="header">
        <h2>Web Application</h2>
        <div class="user-info">
            <span>Welcome, ${authenticatedUser.fullName}</span>
            <a href="logout" class="button secondary">Logout</a>
        </div>
    </div>
    
    <div class="container">
        <h1>Dashboard</h1>
        
        <div class="menu">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="profile.jsp">Profile</a>
            <c:if test="${authenticatedUser.admin}">
                <a href="admin.jsp">Admin</a>
                <a href="users.jsp">Manage Users</a>
            </c:if>
            <a href="index.jsp">Contact Form</a>
        </div>
        
        <div class="card">
            <h2>Welcome to your Dashboard</h2>
            <p>This is a simple dashboard for the web application.</p>
            <p>Your role: <strong>${authenticatedUser.role}</strong></p>
            <p>Your email: ${authenticatedUser.email}</p>
        </div>
        
        <div class="card">
            <h2>Quick Actions</h2>
            <p>
                <a href="index.jsp" class="button">Go to Contact Form</a>
            </p>
        </div>
    </div>
</body>
</html>