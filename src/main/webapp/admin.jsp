<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
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
        .admin-badge {
            background-color: #ff9800;
            color: white;
            padding: 3px 8px;
            border-radius: 3px;
            font-size: 0.8em;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="header">
        <h2>Web Application</h2>
        <div class="user-info">
            <span>Welcome, ${authenticatedUser.fullName} <span class="admin-badge">ADMIN</span></span>
            <a href="logout" class="button secondary">Logout</a>
        </div>
    </div>
    
    <div class="container">
        <h1>Admin Dashboard</h1>
        
        <div class="menu">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="profile.jsp">Profile</a>
            <a href="admin.jsp">Admin</a>
            <a href="users.jsp">Manage Users</a>
            <a href="index.jsp">Contact Form</a>
        </div>
        
        <div class="card">
            <h2>Admin Panel</h2>
            <p>Welcome to the admin panel. Here you can manage the application.</p>
        </div>
        
        <div class="card">
            <h2>System Statistics</h2>
            <p>Here are some system statistics:</p>
            <ul>
                <li>Total Users: ${userCount}</li>
                <li>Active Sessions: ${sessionCount}</li>
                <li>Server Uptime: ${serverUptime}</li>
            </ul>
        </div>
        
        <div class="card">
            <h2>Quick Actions</h2>
            <p>
                <a href="users.jsp" class="button">Manage Users</a>
                <a href="logs.jsp" class="button">View Logs</a>
                <a href="settings.jsp" class="button">System Settings</a>
            </p>
        </div>
    </div>
</body>
</html>