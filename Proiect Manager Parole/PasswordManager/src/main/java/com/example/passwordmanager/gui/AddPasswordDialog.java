package com.example.passwordmanager.gui;

import com.example.passwordmanager.dao.Database;
import com.example.passwordmanager.dao.PasswordDAO;
import com.example.passwordmanager.util.EncryptionUtil;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddPasswordDialog {
    private JDialog dialog;
    private JTextField siteNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private int userId;
    private MainScreen mainScreen;

    public AddPasswordDialog(int userId, MainScreen mainScreen) {
        this.userId = userId;
        this.mainScreen = mainScreen;

        dialog = new JDialog((JFrame) null, "Add Password", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        dialog.add(panel);
        placeComponents(panel);

        dialog.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel siteLabel = new JLabel("Site Name:");
        siteLabel.setBounds(10, 20, 80, 25);
        panel.add(siteLabel);

        siteNameField = new JTextField(20);
        siteNameField.setBounds(100, 20, 165, 25);
        panel.add(siteNameField);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 50, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 50, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 80, 165, 25);
        panel.add(passwordField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(10, 110, 80, 25);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSave();
            }
        });
    }

    public static SecretKey getKeyFromDatabase(String encryptionKeyFromDb) throws Exception {
        // Use SHA-256 to hash the encryption key to 256 bits (32 bytes)
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedKey = digest.digest(encryptionKeyFromDb.getBytes(StandardCharsets.UTF_8));

        // Return a new SecretKeySpec with the hashed key (256-bit key)
        return new SecretKeySpec(hashedKey, "AES");
    }


    public String getUserEncryptionKey(int userId) throws Exception {
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
        return encryptionKey;
    }

    private void handleSave() {
        String siteName = siteNameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            // Retrieve the user's encryption key from the database
            String encryptionKeyFromDb = getUserEncryptionKey(userId); // Replace with actual method to fetch key from DB
            SecretKey key = getKeyFromDatabase(encryptionKeyFromDb); // Convert to valid AES key

            // Encrypt the password using the user's encryption key
            String encryptedPassword = EncryptionUtil.encrypt(password, key);

            // Store the encrypted password in the database
            PasswordDAO passwordDAO = new PasswordDAO();
            if (passwordDAO.addPassword(userId, siteName, username, encryptedPassword)) {
                mainScreen.loadPasswords();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Failed to add password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Encryption error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
