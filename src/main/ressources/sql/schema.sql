-- Active: 1742828094711@@127.0.0.1@3306@java_db


CREATE TABLE IF NOT EXISTS role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- CREATE TABLE IF NOT EXISTS users (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE,
--     password VARCHAR(255) NOT NULL,
--     email VARCHAR(100) NOT NULL UNIQUE,
--     full_name VARCHAR(100) NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     role_id INT,
--     FOREIGN KEY (role_id) REFERENCES role(id),
--     active BOOLEAN DEFAULT TRUE
-- );


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN DEFAULT TRUE
);

-- Insert sample users (password is 'password' hashed with BCrypt)
INSERT INTO users (username, password, email, full_name, role) VALUES 
('admin', '$2a$10$rPiEAgQNIT1TCoKi.XXyIO9aRwjY9xDZRRz0VxLmHU8HKMqzC1zHa', 'admin@example.com', 'Bassirou Diaw', 'ADMIN'),
('user1', '$2a$10$rPiEAgQNIT1TCoKi.XXyIO9aRwjY9xDZRRz0VxLmHU8HKMqzC1zHa', 'user1@example.com', 'Mamadou Niang', 'USER');


SELECT 1;