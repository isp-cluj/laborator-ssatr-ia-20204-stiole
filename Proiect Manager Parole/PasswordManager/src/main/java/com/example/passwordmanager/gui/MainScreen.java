package com.example.passwordmanager.gui;

import com.example.passwordmanager.dao.PasswordDAO;
import com.example.passwordmanager.dao.UserDAO;
import com.example.passwordmanager.util.EncryptionUtil;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainScreen {
    private JFrame frame;
    private JTable passwordTable;
    private PasswordDAO passwordDAO;
    private UserDAO userDAO;
    private int userId;
    private SecretKey userKey;

    public MainScreen(String username) throws Exception {
        userDAO = new UserDAO();
        this.userId = userDAO.getUserId(username);
        this.passwordDAO = new PasswordDAO();

        // Retrieve user's encryption key
        userKey = userDAO.getUserEncryptionKey(userId);

        frame = new JFrame("Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        passwordTable = new JTable();
        loadPasswords();

        JScrollPane scrollPane = new JScrollPane(passwordTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Password");
        JButton deleteButton = new JButton("Delete Password");
        JButton decryptButton = new JButton("Decrypt Password");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(decryptButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPasswordDialog(userId, MainScreen.this);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = passwordTable.getSelectedRow();
                if (selectedRow != -1) {
                    String siteName = (String) passwordTable.getValueAt(selectedRow, 0);
                    passwordDAO.deletePassword(userId, siteName);
                    loadPasswords();
                } else {
                    JOptionPane.showMessageDialog(frame, "Select a password to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = passwordTable.getSelectedRow();
                if (selectedRow != -1) {
                    String encryptedPassword = (String) passwordTable.getValueAt(selectedRow, 2);
                    try {
                        String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword, userKey);
                        JOptionPane.showMessageDialog(frame, "Decrypted Password: " + decryptedPassword, "Decryption Result", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error decrypting password.", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Select a password to decrypt.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    public void loadPasswords() {
        List<String[]> passwords = passwordDAO.getPasswords(userId);
        String[] columnNames = {"Site Name", "Username", "Password (Encrypted)"};
        String[][] data = new String[passwords.size()][3];

        for (int i = 0; i < passwords.size(); i++) {
            data[i][0] = passwords.get(i)[0];
            data[i][1] = passwords.get(i)[1];
            data[i][2] = passwords.get(i)[2]; // Keep the password encrypted here
        }

        passwordTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
