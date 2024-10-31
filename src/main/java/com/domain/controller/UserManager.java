package com.domain.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.domain.model.User;

public class UserManager {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginStatusLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Simulate a simple login check
        if (username.equals("admin") && password.equals("password")) {
            loginStatusLabel.setText("Login successful!");
        } else {
            loginStatusLabel.setText("Invalid credentials. Please try again.");
        }
    }
}
