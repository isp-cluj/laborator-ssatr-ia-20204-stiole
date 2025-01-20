package com.example.passwordmanager;

import com.example.passwordmanager.gui.LoginScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set the look and feel to the system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize the login screen
                new LoginScreen();
            }
        });
    }
}
