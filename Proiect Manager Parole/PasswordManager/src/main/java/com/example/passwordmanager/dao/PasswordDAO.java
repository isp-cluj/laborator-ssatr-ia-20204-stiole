package com.example.passwordmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAO {
    public boolean addPassword(int userId, String siteName, String username, String encryptedPassword) {
        String query = "INSERT INTO passwords (user_id, site_name, username, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, siteName);
            stmt.setString(3, username);
            stmt.setString(4, encryptedPassword);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String[]> getPasswords(int userId) {
        List<String[]> passwords = new ArrayList<>();
        String query = "SELECT site_name, username, password FROM passwords WHERE user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                passwords.add(new String[]{
                        rs.getString("site_name"),
                        rs.getString("username"),
                        rs.getString("password")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwords;
    }

    public boolean deletePassword(int userId, String siteName) {
        String query = "DELETE FROM passwords WHERE user_id = ? AND site_name = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, siteName);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
