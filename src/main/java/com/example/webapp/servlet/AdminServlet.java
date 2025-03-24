package com.example.webapp.servlet;

import com.example.webapp.dao.UserDAO;
import com.example.webapp.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.time.Duration;
// import java.util.concurrent.TimeUnit;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final AuthService authService;
    private final UserDAO userDAO;
    
    public AdminServlet() {
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
        
        // Get statistics
        int userCount = userDAO.getAllUsers().size();
        request.setAttribute("userCount", userCount);
        
        // Get active sessions (simplified)
        int sessionCount = 0;
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null && currentSession.getServletContext() != null) {
            sessionCount = currentSession.getServletContext().getSessionTimeout();
        }
        request.setAttribute("sessionCount", sessionCount);
        
        // Get server uptime
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime();
        Duration uptimeDuration = Duration.ofMillis(uptime);
        String uptimeStr = String.format("%d days, %d hours, %d minutes",
                uptimeDuration.toDays(),
                uptimeDuration.toHours() % 24,
                uptimeDuration.toMinutes() % 60);
        request.setAttribute("serverUptime", uptimeStr);
        
        // Forward to admin page
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}