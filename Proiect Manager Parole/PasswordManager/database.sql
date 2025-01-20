-- Create db
CREATE DATABASE passwords_db;
USE passwords_db;

-- Create tables
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       encryption_key VARCHAR(255) NOT NULL
);


CREATE TABLE passwords (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT NOT NULL,
                           site_name VARCHAR(255) NOT NULL,
                           username VARCHAR(255) NOT NULL,
                           password VARCHAR(255) NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'app_password';
GRANT ALL PRIVILEGES ON passwords_db.* TO 'app_user'@'localhost';
FLUSH PRIVILEGES;

SHOW TABLES;