package com.example.passwordmanager.gui;

import com.example.passwordmanager.dao.UserDAO;
import com.example.passwordmanager.util.PasswordHasher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    handleLogin();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleLogin() throws Exception {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String passwordHash = PasswordHasher.hash(password);

        UserDAO userDAO = new UserDAO();
        if (userDAO.authenticateUser(username, passwordHash)) {
            frame.dispose();
            new MainScreen(username);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String passwordHash = PasswordHasher.hash(password);

        UserDAO userDAO = new UserDAO();
        if (userDAO.registerUser(username, passwordHash)) {
            JOptionPane.showMessageDialog(frame, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Registration failed. Try a different username.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
