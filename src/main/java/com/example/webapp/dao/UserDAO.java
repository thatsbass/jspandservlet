package com.example.webapp.dao;

import com.example.webapp.model.User;
import com.example.webapp.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    // Authentification utilisateur (sans hachage)
    public Optional<User> authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND active = TRUE";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password"); // Mot de passe en clair
                    if (password.equals(storedPassword)) { // Comparaison directe
                        return Optional.of(mapUser(rs));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'authentification : " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // Trouver un utilisateur par ID
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapUser(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur par ID : " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // Trouver un utilisateur par username
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapUser(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur par username : " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // Créer un nouvel utilisateur (sans hachage)
    public boolean createUser(User user, String plainPassword) {
        String sql = "INSERT INTO users (username, password, email, full_name, role, active) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, plainPassword); // Mot de passe stocké en clair
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getFullName());
            pstmt.setString(5, user.getRole());
            pstmt.setBoolean(6, user.isActive());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 1) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getLong(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    // Mettre à jour un utilisateur
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET email = ?, full_name = ?, role = ?, active = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getFullName());
            pstmt.setString(3, user.getRole());
            pstmt.setBoolean(4, user.isActive());
            pstmt.setLong(5, user.getId());

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // Mettre à jour le mot de passe d'un utilisateur (sans hachage)
    public boolean updatePassword(Long userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword); // Mot de passe stocké en clair
            pstmt.setLong(2, userId);

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // Supprimer un utilisateur
    public boolean deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // Mapper un utilisateur depuis un ResultSet
    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setFullName(rs.getString("full_name"));
        user.setRole(rs.getString("role"));
        user.setActive(rs.getBoolean("active"));
        return user;
    }
}
