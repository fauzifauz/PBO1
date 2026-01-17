-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS krl_system;
USE krl_system;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') DEFAULT 'USER',
    balance DOUBLE DEFAULT 0.0
);

-- Stations table
CREATE TABLE IF NOT EXISTS stations (
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    distance_from_start DOUBLE NOT NULL
);

-- Transactions table
CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    origin_station_id VARCHAR(10),
    destination_station_id VARCHAR(10),
    amount DOUBLE NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (origin_station_id) REFERENCES stations(id),
    FOREIGN KEY (destination_station_id) REFERENCES stations(id)
);

-- Tariffs table
CREATE TABLE IF NOT EXISTS tariffs (
    id INT PRIMARY KEY,
    base_fare DOUBLE NOT NULL,
    fare_per_km DOUBLE NOT NULL
);

-- Initial Data
INSERT IGNORE INTO users (username, password, role, balance) VALUES ('admin', 'admin123', 'ADMIN', 0.0);
INSERT IGNORE INTO users (username, password, role, balance) VALUES ('user', 'user123', 'USER', 50000.0);

INSERT IGNORE INTO stations (id, name, distance_from_start) VALUES 
('JAK', 'Jakarta Kota', 0.0),
('MTR', 'Manggarai', 6.0),
('DPK', 'Depok', 25.0),
('BOO', 'Bogor', 54.0);

INSERT IGNORE INTO tariffs (id, base_fare, fare_per_km) VALUES (1, 3000.0, 100.0);
