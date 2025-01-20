package com.example.passwordmanager.dao;

import com.example.passwordmanager.util.EncryptionUtil;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Method to register user and store encryption key
    public boolean registerUser(String username, String passwordHash) {
        String query = "INSERT INTO users (username, password_hash, encryption_key) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, passwordHash);

            // Generate a new key for the user and store it
            SecretKey key = EncryptionUtil.generateKey();
            String encryptedKey = EncryptionUtil.keyToString(key); // Store the key as a string

            stmt.setString(3, encryptedKey);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to authenticate user and retrieve encryption key
    public boolean authenticateUser(String username, String passwordHash) {
        String query = "SELECT id, encryption_key FROM users WHERE username = ? AND password_hash = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String encryptedKey = rs.getString("encryption_key");

                // Decrypt the stored key
                SecretKey key = EncryptionUtil.getKeyFromString(encryptedKey);
                // Store the key temporarily for the user session
                // You can add this key to the session or pass it on to MainScreen for decryption
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static SecretKey getKeyFromDatabase(String encryptionKeyFromDb) throws Exception {
        // Use SHA-256 to hash the encryption key to 256 bits (32 bytes)
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedKey = digest.digest(encryptionKeyFromDb.getBytes(StandardCharsets.UTF_8));

        // Return a new SecretKeySpec with the hashed key (256-bit key)
        return new SecretKeySpec(hashedKey, "AES");
    }


    // Method to get the user's encryption key
    public SecretKey getUserEncryptionKey(int userId) throws SQLException, Exception {
        String encryptionKey = null;
        String query = "SELECT encryption_key FROM users WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    encryptionKey = rs.getString("encryption_key");
                }
            }
        }

        // Return the processed encryption key (hashed or padded)
        return getKeyFromDatabase(encryptionKey);
    }


    public int getUserId(String username) {
        String query = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
