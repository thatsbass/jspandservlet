package com.example.webapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/java_db";
            String user = "java_user";
            String password = "javaSecure";
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie à la base de données !");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Pilote MySQL non trouvé", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                instance = null;
            }
        }
    }
}




// package com.example.webapp.config;

// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;

// import java.io.IOException;
// import java.io.InputStream;
// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.Properties;

// public class DatabaseConnection {
//     private static final HikariDataSource dataSource;

//     static {
//         Properties properties = new Properties();
        
//         try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
//             if (input == null) {
//                 throw new RuntimeException("Fichier application.properties introuvable");
//             }
//             properties.load(input);
//         } catch (IOException e) {
//             throw new RuntimeException("Erreur lors du chargement des propriétés", e);
//         }

//         // Déterminer le type de base de données (MySQL ou PostgreSQL)
//         String dbType = properties.getProperty("database.type", "mysql"); // Par défaut MySQL
//         String url = properties.getProperty(dbType + ".url");
//         String user = properties.getProperty(dbType + ".user");
//         String password = properties.getProperty(dbType + ".password");

//         // Configurer HikariCP
//         HikariConfig config = new HikariConfig();
//         config.setJdbcUrl(url);
//         config.setUsername(user);
//         config.setPassword(password);
//         config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("database.poolSize", "10")));
//         config.setMinimumIdle(Integer.parseInt(properties.getProperty("database.minIdle", "5")));
//         config.setConnectionTestQuery("SELECT 1");

//         // Initialisation du pool de connexions
//         dataSource = new HikariDataSource(config);
//     }

//     private DatabaseConnection() {
//         // Empêcher l'instanciation
//     }

//     public static Connection getConnection() throws SQLException {
//         return dataSource.getConnection();
//     }

//     public static void closeDataSource() {
//         if (dataSource != null && !dataSource.isClosed()) {
//             dataSource.close();
//         }
//     }
// }


// package com.example.webapp.config;

// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;

// import java.sql.Connection;
// import java.sql.SQLException;

// public class DatabaseConnection {
//     private static final HikariDataSource dataSource;
    
//     static {
//         // Configuration for connection pool
//         HikariConfig config = new HikariConfig();
//         config.setJdbcUrl("jdbc:mysql://localhost:3306/java_db");
//         config.setUsername("java_user"); // Change to your MySQL username
//         config.setPassword("javaSecure"); // Change to your MySQL password
//         config.setMaximumPoolSize(10);
//         config.setMinimumIdle(5);
//         config.setIdleTimeout(300000);
//         config.setMaxLifetime(600000);
//         config.setConnectionTestQuery("SELECT 1");
//         config.setAutoCommit(true);
        
//         // Initialize the data source
//         dataSource = new HikariDataSource(config);
//     }
    
//     private DatabaseConnection() {
//         // Private constructor to prevent instantiation
//     }
    
//     public static Connection getConnection() throws SQLException {
//         return dataSource.getConnection();
//     }
    
//     public static void closeDataSource() {
//         if (dataSource != null && !dataSource.isClosed()) {
//             dataSource.close();
//         }
//     }
// }