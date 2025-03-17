<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Submission Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
        }
        .info-box {
            background-color: #e9f7ef;
            padding: 15px;
            border-radius: 4px;
            margin-top: 20px;
        }
        .label {
            font-weight: bold;
            margin-right: 5px;
        }
        .button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            margin-top: 15px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Form Submission Successful</h1>
        
        <div class="info-box">
            <p><span class="label">Name:</span> ${user.name}</p>
            <p><span class="label">Email:</span> ${user.email}</p>
            <p><span class="label">Message:</span> ${user.message}</p>
        </div>
        
        <a href="index.jsp" class="button">Back to Form</a>
    </div>
</body>
</html>