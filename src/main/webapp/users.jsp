<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
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
            cursor: pointer;
            border: none;
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
        .button.blue {
            background-color: #2196F3;
        }
        .button.blue:hover {
            background-color: #0b7dda;
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
        }
        .user-badge {
            background-color: #2196F3;
            color: white;
            padding: 3px 8px;
            border-radius: 3px;
            font-size: 0.8em;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .success-message {
            color: #4CAF50;
            margin-bottom: 15px;
        }
        .error-message {
            color: #f44336;
            margin-bottom: 15px;
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
        <h1>User Management</h1>
        
        <div class="menu">
            <a href="dashboard.jsp">Dashboard</a>
            <a href="profile.jsp">Profile</a>
            <a href="admin.jsp">Admin</a>
            <a href="users.jsp">Manage Users</a>
            <a href="index.jsp">Contact Form</a>
        </div>
        
        <c:if test="${not empty successMessage}">
            <div class="success-message">
                ${successMessage}
            </div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ${errorMessage}
            </div>
        </c:if>
        
        <div class="card">
            <h2>Create New User</h2>
            <form action="users" method="post">
                <input type="hidden" name="action" value="create">
                
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" required>
                </div>
                
                <div class="form-group">
                    <label for="role">Role:</label>
                    <select id="role" name="role" required>
                        <option value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </div>
                
                <button type="submit" class="button">Create User</button>
            </form>
        </div>
        
        <div class="card">
            <h2>Users List</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Full Name</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.fullName}</td>
                            <td>
                                <c:if test="${user.role eq 'ADMIN'}">
                                    <span class="admin-badge">ADMIN</span>
                                </c:if>
                                <c:if test="${user.role eq 'USER'}">
                                    <span class="user-badge">USER</span>
                                </c:if>
                            </td>
                            <td>${user.active ? 'Active' : 'Inactive'}</td>
                            <td>
                                <button class="button blue" onclick="editUser('${user.id}', '${user.email}', '${user.fullName}', '${user.role}', '${user.active}')">Edit</button>
                                <form action="users" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <button type="submit" class="button secondary" onclick="return confirm('Are you sure you want to delete this user?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <!-- Edit User Modal (simplified) -->
    <div id="editModal" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.4); z-index: 1000;">
        <div style="background-color: white; margin: 10% auto; padding: 20px; width: 50%; border-radius: 5px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
            <h2>Edit User</h2>
            <form id="editForm" action="users" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" id="editId" name="id">
                
                <div class="form-group">
                    <label for="editEmail">Email:</label>
                    <input type="email" id="editEmail" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="editFullName">Full Name:</label>
                    <input type="text" id="editFullName" name="fullName" required>
                </div>
                
                <div class="form-group">
                    <label for="editRole">Role:</label>
                    <select id="editRole" name="role" required>
                        <option value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label>
                        <input type="checkbox" id="editActive" name="active"> Active
                    </label>
                </div>
                
                <button type="submit" class="button">Update User</button>
                <button type="button" class="button secondary" onclick="closeEditModal()">Cancel</button>
            </form>
        </div>
    </div>
    
    <script>
        function editUser(id, email, fullName, role, active) {
            document.getElementById('editId').value = id;
            document.getElementById('editEmail').value = email;
            document.getElementById('editFullName').value = fullName;
            document.getElementById('editRole').value = role;
            document.getElementById('editActive').checked = active;
            
            document.getElementById('editModal').style.display = 'block';
        }
        
        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
        }
        
        // Close the modal if user clicks outside of it
        window.onclick = function(event) {
            var modal = document.getElementById('editModal');
            if (event.target == modal) {
                closeEditModal();
            }
        }
    </script>
</body>
</html>